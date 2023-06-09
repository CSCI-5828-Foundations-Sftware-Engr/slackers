package com.goodboards.tests

import com.goodboards.tests.client.ClientUtil
import com.goodboards.tests.client.TemplateUtil
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GameTest {

    companion object {

        val GAME_ID = "129c0345-a22c-4193-ac83-02500186d76d"
        val GAME_NAME = "Uno"
        val GAME_DESCRIPTION= "Friendship destroyer."

        fun getGamePagePath(id: String): String {
            return EnvironmentConfig.Staging.URL + "game/$id"
        }

        fun checkGame(body: Element) {
            assertTrue(body.select("div.game").isNotEmpty())
            assertTrue(body.select("h1.game-name").isNotEmpty())
            assertTrue(body.select("h1.game-name").text().contains(GAME_NAME))
            assertTrue(body.select("div.game-description").isNotEmpty())
            assertTrue(body.select("div.game-description").text().contains(GAME_DESCRIPTION))
        }

        fun checkNews(body: Element) {
            assertTrue(body.select("h3.news-header").isNotEmpty())
            assertTrue(body.select("div.news-entry").map { it.select("a") }.all { it.isNotEmpty() })
            assertTrue(body.select("div.news-title").isNotEmpty())
            assertTrue(body.select("p.news-description").isNotEmpty())
        }
    }

    @Test
    fun `get game page`(): Unit = runBlocking {
        val response = ClientUtil.getClient().get(urlString = getGamePagePath(GAME_ID))
        assertTrue(response.status == HttpStatusCode.OK)
        val htmlResponse = Jsoup.parse(response.bodyAsText())
        assertNotNull(htmlResponse.head())
        TemplateUtil.checkHead(htmlResponse.head())
        assertNotNull(htmlResponse.body())
        TemplateUtil.checkNavBar(htmlResponse.body())
        // Check for Game things.
        checkGame(htmlResponse.body())
    }

    @Test
    fun `get game page has news`(): Unit = runBlocking {
        val response = ClientUtil.getClient().get(urlString = getGamePagePath(GAME_ID))
        assertTrue(response.status == HttpStatusCode.OK)
        val htmlResponse = Jsoup.parse(response.bodyAsText())
        assertNotNull(htmlResponse.head())
        TemplateUtil.checkHead(htmlResponse.head())
        assertNotNull(htmlResponse.body())
        TemplateUtil.checkNavBar(htmlResponse.body())
        checkNews(htmlResponse.body())
    }
}