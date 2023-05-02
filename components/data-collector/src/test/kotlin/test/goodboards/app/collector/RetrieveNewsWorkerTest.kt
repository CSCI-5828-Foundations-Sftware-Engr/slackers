package test.goodboards.app.collector

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.app.collector.RetrieveNewsWorker
import com.goodboards.app.collector.Wrapper
import com.goodboards.app.collector.HttpClientWrapper
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
        // Mock Database
        val mockedNewsApiKey = "mockedNewsAPIKey"
        val mockedDBInterface = DBMock.mockDBInterface()
        val mockedRedisInterface = mockk<RedisInterface>()
        val mockedHttpClientWrapper: HttpClientWrapper = mockk(relaxed = true)

        // Mock Wrapper
        mockkObject(Wrapper)
        every { Wrapper.getEnv("NEWS_API_KEY") } returns mockedNewsApiKey
        every { Wrapper.getDBInterface() } returns mockedDBInterface
        every { Wrapper.getRedisInterface() } returns mockedRedisInterface
        every { Wrapper.getHttpClient() } returns mockedHttpClientWrapper

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
            coEvery { mockedHttpClientWrapper.get(url) } returns mockedHttpResponse
        }
        val rawArticle = """{"status":"ok","totalResults":1,"articles":[{"source":{"id":"fake-news","name":"Fake News"},"author":"Fake Author","title":"Fake Title","description":"Fake Description","url":"Fake URL","urlToImage":"Fake Image URL","publishedAt":"Fake Timestamp","content":"Fake Content"}]}"""
        coEvery { mockedHttpResponse.readText() } returns rawArticle

        // Mock Redis Calls
        val newsUnits = listOf(
            "{\"gameID\":\"1\",\"title\":\"Fake Title\",\"description\":\"Fake Description\",\"url\":\"Fake URL\"}",
            "{\"gameID\":\"2\",\"title\":\"Fake Title\",\"description\":\"Fake Description\",\"url\":\"Fake URL\"}"
        )
        every { mockedRedisInterface.pushToList("news:collect-analyze", newsUnits) } returns Unit

        // ----- Test -----
        val task = RetrieveNewsTask("Happy Path Test")
        val worker = RetrieveNewsWorker()
        worker.execute(task)

        // ----- Expect -----
        // : No exceptions thrown
    }

    @Test
    fun testExecuteNoArticles() = runTest {

        // ----- Setup -----
        // Mock Wrapper
        val mockedNewsApiKey = "mockedNewsAPIKey"
        val mockedDBInterface = DBMock.mockDBInterface()
        val mockedRedisInterface = mockk<RedisInterface>()
        val mockedHttpClientWrapper: HttpClientWrapper = mockk(relaxed = true)
        mockkObject(Wrapper)
        every { Wrapper.getEnv("NEWS_API_KEY") } returns mockedNewsApiKey
        every { Wrapper.getDBInterface() } returns mockedDBInterface
        every { Wrapper.getRedisInterface() } returns mockedRedisInterface
        every { Wrapper.getHttpClient() } returns mockedHttpClientWrapper

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
            coEvery { mockedHttpClientWrapper.get(url) } returns mockedHttpResponse
        }
        val rawArticle = """{"status":"ok","totalResults":0,"articles":[]}"""
        coEvery { mockedHttpResponse.readText() } returns rawArticle

        // Mock Redis Calls
        val newsUnits = listOf<String>()
        every { mockedRedisInterface.pushToList("news:collect-analyze", newsUnits) } throws Exception("ERROR: no articles to add")

        // ----- Test -----
        val task = RetrieveNewsTask("No Articles Found Test")
        val worker = RetrieveNewsWorker()
        worker.execute(task)

        // ----- Expect -----
        // : No exceptions thrown
    }

}