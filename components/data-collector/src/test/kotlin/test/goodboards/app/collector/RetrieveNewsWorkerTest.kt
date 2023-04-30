package test.goodboards.app.collector

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.app.collector.RetrieveNewsWorker
import com.goodboards.app.collector.Wrappers
import com.goodboards.db.DBInterface
import com.goodboards.db.Game
import com.goodboards.redis.RedisInterface
import io.ktor.client.*
import io.ktor.client.statement.*
import org.junit.Test
import io.mockk.*

class RetrieveNewsWorkerTest {

    // Test if entire path went well
    @Test
    fun testExecuteHappyPath() {

        // ----- Setup -----
        // Mock Wrapper
        val mockedWrappers: Wrappers = mockk(relaxed = true)
        val mockedNewsApiKey = "mockNewsApiKey"
        val mockedDBInterface: DBInterface = mockk(relaxed = true)
        val mockedRedisInterface: RedisInterface = mockk(relaxed = true)
        val mockedHttpClient: HttpClient = mockk(relaxed = true)
        every { mockedWrappers.getenv("NEWS_API_KEY") } returns mockedNewsApiKey
        every { mockedWrappers.getDBInterface() } returns mockedDBInterface
        every { mockedWrappers.getRedisInterface() } returns mockedRedisInterface
        every { mockedWrappers.getHttpClient() } returns mockedHttpClient

        // Mock DB calls
        val games = mutableListOf(
            Game("1", "Multi Word Name", "Classic Chess"),
            Game("2", "Uno", "Classic Uno")
        )
        every { mockedDBInterface.getAllGames() } returns games

        // Mock HttpClient calls
        val formattedGameNames = listOf("multi_word_name", "uno")
        val mockedHttpResponse: HttpResponse = mockk(relaxed = true)
        for(name in formattedGameNames) {
            val url = "https://newsapi.org/v2/everything?q=$name&pageSize=10&apiKey=$mockedNewsApiKey"
            //every { mockedHttpClient.get(url) } returns mockedHttpResponse
        }
        val rawArticle = """
            {"status":"ok","totalResults":1,"articles":[{"source":{"id":"fake-news","name":"Fake News"},"author":"Fake Author","title":"Fake Title","description":"Fake Description","url":"Fake URL","urlToImage":"Fake Image URL","publishedAt":"Fake Timestamp","content":"Fake Content"}]}
        """.trimIndent()
        //every { mockedHttpResponse.readText() } returns rawArticle

        // Mock Redis Calls
        val newsUnits = arrayOf(
            """{
                "gameID": "1",
                "title": "Fake Title",
                "description": "Fake Description", 
                "url": "Fake URL"
            }""",
            """{
                "gameID": "2",
                "title": "Fake Title",
                "description": "Fake Description", 
                "url": "Fake URL"
            }"""
        )
        every { mockedRedisInterface.pushToList("news:collect-analyze", *newsUnits) } returns Unit

        // ----- Test -----
        val task = RetrieveNewsTask("Happy Path Test")
        val worker = RetrieveNewsWorker()
        worker.execute(task)

        // ----- Expect -----
        // : articles to be added to redis queue

    }

}