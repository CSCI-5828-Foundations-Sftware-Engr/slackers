package test.goodboards.app.collector

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.app.collector.RetrieveNewsWorker
import com.goodboards.app.collector.Wrappers
import com.goodboards.app.collector.HttpClientWrapper
import com.goodboards.db.DBInterface
import com.goodboards.db.Game
import com.goodboards.redis.RedisInterface
import io.ktor.client.statement.*
import org.junit.Test
import org.junit.Before
import io.mockk.*
import kotlinx.coroutines.test.*

class RetrieveNewsWorkerTest {

    @Before
    fun setup() {
        mockkStatic(HttpResponse::readText)
    }

    @Test
    fun testExecuteHappyPath() = runTest {

        // ----- Setup -----
        // Mock Wrapper
        val mockedWrappers: Wrappers = mockk(relaxed = true)
        val mockedNewsApiKey = "mockNewsApiKey"
        val mockedDBInterface: DBInterface = mockk(relaxed = true)
        val mockedRedisInterface: RedisInterface = mockk(relaxed = true)
        val mockedHttpClient: HttpClientWrapper = mockk(relaxed = true)
        every { mockedWrappers.getEnv("NEWS_API_KEY") } returns mockedNewsApiKey
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
            val url = "https://newsapi.org/v2/everything?q=$name&language=en&pageSize=10&apiKey=$mockedNewsApiKey"
            coEvery { mockedHttpClient.get(url) } returns mockedHttpResponse
        }
        val rawArticle = """{"status":"ok","totalResults":1,"articles":[{"source":{"id":"fake-news","name":"Fake News"},"author":"Fake Author","title":"Fake Title","description":"Fake Description","url":"Fake URL","urlToImage":"Fake Image URL","publishedAt":"Fake Timestamp","content":"Fake Content"}]}"""
        coEvery { mockedHttpResponse.readText() } returns rawArticle

        // Mock Redis Calls
        val newsUnits = arrayOf(
            """{
                "gameID": "1",
                "title": "Fake Title",
                "description": "Fake Description", 
                "url": "Fake URL"
            }""".trimIndent(),
            """{
                "gameID": "2",
                "title": "Fake Title",
                "description": "Fake Description", 
                "url": "Fake URL"
            }""".trimIndent()
        )
        every { mockedRedisInterface.pushToList("news:collect-analyze", *newsUnits) } returns Unit

        // ----- Test -----
        val task = RetrieveNewsTask("Happy Path Test")
        val worker = RetrieveNewsWorker()
        worker.execute(task)

        // ----- Expect -----
        // : No exceptions thrown
    }

}