package test.goodboards.app.analyzer

import com.goodboards.app.analyzer.*
import com.goodboards.db.DBInterface
import com.goodboards.db.News
import com.goodboards.redis.RedisInterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertEquals


class AnalyzerWorkerTest{
    // test if the news has not been included in db

    @Test
    fun testSetDoubleApostrophe(){
        val mockedInput = "I'm"
        assertEquals("I''m", AnalyzerWorkerHelper.setDoubleApostrophe(mockedInput))
    }
    @Test
    fun testNewsNotIncludedinDB(){
        // mock the news unit that will be added
        val newsUnits = listOf<String>(
            """{
                "gameID": "Fake gameId",
                "title": "Fake title",
                "description": "Fake description",
                "url": "Fake URL"
            }""".trimIndent()
        )
        mockkObject(Wrapper)
        val mockedRedisInterface: RedisInterface = mockk(relaxed = true)
        val mockedDBInterface: DBInterface = mockk(relaxed = true)
        val mockedNewsResponse: List<News> = listOf()

        every { Wrapper.getRedisInterface() } returns mockedRedisInterface
        every { Wrapper.getRandomUUID()} returns "FakeUUID1"
        every { Wrapper.getDBInterface() } returns mockedDBInterface
        every { mockedRedisInterface.getFromList("news:collect-analyze", 10)} returns newsUnits
        every { mockedDBInterface.getNewsBasedOnTitle("Fake title") } returns mockedNewsResponse

        val newsItem = NewsUnit("Fake gameId", "Fake title", "Fake description", "Fake URL")
        every { mockedDBInterface.addNews(newsItem.title, newsItem.description, newsItem.url, newsItem.gameID, "FakeUUID1") } returns Unit

        val task = AnalyzerTask("Analyzer Task test")
        val worker = AnalyzerWorker()
        worker.execute(task)
        verify { mockedDBInterface.addNews(newsItem.title, newsItem.description, newsItem.url, newsItem.gameID, "FakeUUID1") }

    }

    @Test
    fun testNewsAlreadyinDB(){
        val newsUnits = listOf<String>("""{
                "gameID": "Fake gameId",
                "title": "Fake title",
                "description": "Fake description",
                "url": "Fake URL"
            }""")
        mockkObject(Wrapper)
        val mockedRedisInterface: RedisInterface = mockk(relaxed = true)
        val mockedDBInterface: DBInterface = mockk(relaxed = true)
        val mockedNewsResponse: List<News> = listOf(News("Fake id","Fake gameid", "Fake title reply", "Fake description reply", "Fake URL reply"))
        every { Wrapper.getRedisInterface() } returns mockedRedisInterface
        every { Wrapper.getRandomUUID()} returns "FakeUUID1"
        every { Wrapper.getDBInterface() } returns mockedDBInterface
        every { mockedRedisInterface.getFromList("news:collect-analyze", 10)} returns newsUnits
        every { mockedDBInterface.getNewsBasedOnTitle("Fake title") } returns mockedNewsResponse

        val task = AnalyzerTask("Analyzer Task test")
        val worker = AnalyzerWorker()
        worker.execute(task)
    }

}