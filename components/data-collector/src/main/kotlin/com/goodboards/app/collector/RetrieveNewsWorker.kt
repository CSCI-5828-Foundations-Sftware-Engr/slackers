package com.goodboards.app.collector

import com.goodboards.workflow.Worker
import com.goodboards.db.*
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
    private val NEWS_API_KEY = System.getenv("NEWS_API_KEY")
    private val logger = LoggerFactory.getLogger(this.javaClass)
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

            // todo - data collection happens here

            // Get all games in the database
//            val url = System.getenv("DATABASE_URL")
//            val user = System.getenv("DATABASE_USERNAME")
//            val pass = System.getenv("DATABASE_PASSWORD")
//            DBConnection.setConnection(url, user, pass)
//            val dbInterface = DBInterface(DBConnection)
//            val games = dbInterface.getAllGames()

            // TODO: when local db is working again, scrap this code and use above
            val games = listOf(
                Game("1", "Uno", "Terrifying"),
                Game("2", "Chess", "Classic"),
                Game("3", "Poker", "Slippery slope")
            )

            // Query news for all games in the database
            val newsUnits = mutableListOf<NewsUnit>()
            for(game in games) {

                // Format game name for API call
                val name = game.name.replace(" ", "_").lowercase()

                // Get data and deserialize
                val newsRaw: HttpResponse = client.get("https://newsapi.org/v2/everything?q=$name&pageSize=10&apiKey=$NEWS_API_KEY")
                val newsResponse = format.decodeFromString<NewsResponse>(newsRaw.readText())

                // Package response into units of work for redis
                for(article in newsResponse.articles) {
                    if(article.title.isEmpty() || article.description.isEmpty() || article.url.isEmpty()) {
                        continue
                    }
                    newsUnits.add(NewsUnit(game.uuid, article.title, article.description, article.url))
                }
            }
            println(newsUnits)

            // Put news in Redis

            logger.info("completed data collection.")
        }
    }
}