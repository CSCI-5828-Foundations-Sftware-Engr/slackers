package test.goodboards.app.game

import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertNotNull
import org.jsoup.Jsoup
import org.junit.Test
import test.goodboards.app.BaseAppTest
import test.goodboards.app.util.GamesMock
import test.goodboards.app.util.HTMLUtil
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetGamesTest : BaseAppTest() {


    @Test
    fun testGamesPageTitleAndContact() = testApp {
        GamesMock.mockGames(3)
        handleRequest(HttpMethod.Get, "/").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("GOODBOARDS"))
            assertTrue(response.content!!.contains("Contact"))
        }
    }

    @Test
    fun testGetData() = testApp {
        GamesMock.mockGames(3)
        handleRequest(HttpMethod.Get, "/").apply {
            val htmlResponse = Jsoup.parse(response.content!!)
            assertNotNull(htmlResponse.body())
            val gameElements = HTMLUtil.extractGameElements(htmlResponse.body())
            assertEquals(3, gameElements.size)
        }
    }

}
