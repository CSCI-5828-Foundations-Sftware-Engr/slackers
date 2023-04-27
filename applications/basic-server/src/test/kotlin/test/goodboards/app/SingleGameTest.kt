package test.goodboards.app

import com.goodboards.app.game.Game
import io.ktor.http.*
import io.ktor.server.testing.*
import org.jsoup.Jsoup
import org.junit.Ignore
import org.junit.Test
import test.goodboards.app.util.GamesMock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SingleGameTest : BaseAppTest() {
    @Test
    fun testSingleGameResponse() = testApp {
        val game: Game = GamesMock.mockGames(1)[0]
        handleRequest(HttpMethod.Get, "/game/${game.id}").apply {
            assertEquals(200, response.status()?.value)
            assertNotNull(response.content)
            val htmlResponse = Jsoup.parse(response.content)
            assertNotNull(htmlResponse.body())
            assertNotNull(htmlResponse.body().select("h1.game-name"))
            assertTrue(htmlResponse.body().select("h1.game-name").text().contains(game.name))
            assertNotNull(htmlResponse.body().select("div.game-description"))
            assertTrue(htmlResponse.body().select("div.game-description").text().contains(game.description))
        }
    }

    @Ignore
    @Test
    fun testGameDetails() = testApp{
        handleRequest ( HttpMethod.Get, "/game/0" ).apply{
            assertTrue(response.content!!.contains("Uno"))
            assertTrue(response.content!!.contains("typical friendship destroying game"))
        }
    }
    @Ignore
    @Test
    fun testNewsHeader() = testApp {
        handleRequest (HttpMethod.Get, "game/0").apply{
            assertTrue(response.content!!.contains("News"))
            assertTrue(response.content!!.contains("Description"))
            assertTrue(response.content!!.contains("Source"))
        }
    }
    @Ignore
    @Test
    fun testNewsDetails() = testApp {
        handleRequest (HttpMethod.Get, "game/0").apply{
            assertTrue(response.content!!.contains("The Computational Anatomy of Human Values"))
            println(response.content)
            assertTrue(response.content!!.contains("Published on April 6, 2023 10:33 AM GMTThis is crossposted from my personal blog.Epistemic Status:"))
            assertTrue(response.content!!.contains("Lesswrong.com"))
        }
    }
}