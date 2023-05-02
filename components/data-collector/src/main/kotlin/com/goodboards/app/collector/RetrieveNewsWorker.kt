package com.goodboards.app.collector

import com.goodboards.workflow.Worker
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.net.URLEncoder

class RetrieveNewsWorker(override val name: String = "data-collector") : Worker<RetrieveNewsTask> {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val redisInterface = Wrapper.getRedisInterface()
    private val dbInterface = Wrapper.getDBInterface()
    private val apiKey = Wrapper.getEnv("NEWS_API_KEY")
    private val client = Wrapper.getHttpClient()
    private val format = Json {
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
                val url = "https://newsapi.org/v2/everything?q=$name&language=en&pageSize=10&apiKey=$apiKey"
                val encodedUrl = URLEncoder.encode(url, "UTF-8")

                // Get data and deserialize
                try {
                    // TODO: use a builder instead
                    val newsRaw: HttpResponse = client.get(encodedUrl)
                    val newsResponse = format.decodeFromString<NewsResponse>(newsRaw.readText())

                    // Package response into units of work for redis
                    for(article in newsResponse.articles) {
                        if(article.title.isNotEmpty() && article.description.isNotEmpty() && article.url.isNotEmpty()) {
                            val unit = NewsUnit(game.uuid, article.title, article.description, article.url)
                            newsUnits.add(format.encodeToString(unit))
                        }
                    }
                } catch (e: Exception) {
                    logger.error("Failed to fetch news with error $e")
                }
            }

            // Put news in Redis if there is any
            if(newsUnits.isNotEmpty())
            {
                redisInterface.pushToList(name = "news:collect-analyze", newsUnits)
            }

            logger.info("completed data collection.")
        }
    }
}