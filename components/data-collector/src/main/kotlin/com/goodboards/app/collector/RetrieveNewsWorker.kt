package com.goodboards.app.collector

import com.goodboards.workflow.Worker
import com.goodboards.db.*
import com.goodboards.redis.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.features.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class RetrieveNewsWorker(override val name: String = "data-collector") : Worker<RetrieveNewsTask> {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val redisInterface = RedisInterface()

    // Connect to database
    val dbCreds = DBConnection.getCredentials()
    val status = DBConnection.setConnection(dbCreds.url, dbCreds.username, dbCreds.password)
    private val dbInterface = DBInterface(DBConnection)

    // Connect to News API via HTTP
    private val NEWS_API_KEY = System.getenv("NEWS_API_KEY")
    private val client = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
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
                val newsRaw: HttpResponse = client.get("https://newsapi.org/v2/everything?q=$name&language=en&pageSize=10&apiKey=$NEWS_API_KEY")
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

            // Put news in Redis
            val arrNewsUnits = newsUnits.toTypedArray()
            redisInterface.pushToList(key = "news:collect-analyze", *arrNewsUnits)

            logger.info("completed data collection.")
        }
    }
}