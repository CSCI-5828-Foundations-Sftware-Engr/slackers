package com.goodboards.app.collector

import com.goodboards.workflow.Worker
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class RetrieveNewsWorker(override val name: String = "data-collector") : Worker<RetrieveNewsTask> {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val redisInterface = Wrappers.getRedisInterface()
    private val dbInterface = Wrappers.getDBInterface()
    private val apiKey = Wrappers.getEnv("NEWS_API_KEY")
    private val client = Wrappers.getHttpClient()
    private val format = Json {
        prettyPrint = true
        coerceInputValues = true
    }

    override fun execute(task: RetrieveNewsTask) {
        runBlocking {
            logger.info("starting data collection.")

            // Query news for all games in the database
            val games = dbInterface.getAllGames()
            val newsUnits = mutableListOf<String>()
            for(game in games) {

                // Format game name for API call
                val name = game.name.replace(" ", "_").lowercase()

                // Get data and deserialize
                val newsRaw: HttpResponse = client.get("https://newsapi.org/v2/everything?q=$name&language=en&pageSize=10&apiKey=$apiKey")
                val newsResponse = format.decodeFromString<NewsResponse>(newsRaw.readText())

                // Package response into units of work for redis
                for(article in newsResponse.articles) {
                    if(article.title.isEmpty() || article.description.isEmpty() || article.url.isEmpty()) {
                        continue
                    }
                    val unit = NewsUnit(game.uuid, article.title, article.description, article.url)
                    newsUnits.add(format.encodeToString(unit))
                }
            }

            // Put news in Redis if there is any
            if(newsUnits.size != 0)
            {
                val arrNewsUnits = newsUnits.toTypedArray()
                redisInterface.pushToList(name = "news:collect-analyze", *arrNewsUnits)
            }

            logger.info("completed data collection.")
        }
    }
}