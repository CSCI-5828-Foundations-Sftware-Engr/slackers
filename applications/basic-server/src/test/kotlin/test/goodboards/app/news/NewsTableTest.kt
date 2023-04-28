package test.goodboards.app.news

import com.goodboards.app.game.Game
import io.ktor.http.*
import io.ktor.server.testing.*
import org.jsoup.Jsoup
import org.junit.Test
import test.goodboards.app.BaseAppTest
import test.goodboards.app.util.GamesMock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NewsTableTest : BaseAppTest() {
    @Test
    fun testNewsTable() = testApp {
        val game: Game = GamesMock.mockGames(1)[0]
        handleRequest(HttpMethod.Get, "/game/${game.id}").apply {
            assertEquals(200, response.status()?.value)
            assertNotNull(response.content)
            val htmlResponse = Jsoup.parse(response.content)
            assertNotNull(htmlResponse.body())
            assertNotNull(htmlResponse.body().select("div[id=table-news]"))
        }
    }
}