package com.goodboards.tests

import com.goodboards.tests.client.ClientUtil
import io.ktor.client.statement.*
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GamesTest {
    companion object {
        fun checkHead(head: Element) {
            // Title
            assertTrue(head.select("title").isNotEmpty())
            assertTrue(head.select("title").text().equals("Team Slackers"))
        }

        fun checkNavBar(body: Element) {
            assertTrue(body.select("div#navbar-default").isNotEmpty())
            assertTrue(body.select("a[href='/games']").isNotEmpty())
            assertTrue(body.select("a[href='/contact']").isNotEmpty())
        }

        fun checkGames(body: Element) {
            assertTrue(body.select("p").size == 12)
        }
    }

    @Test
    fun `get home returns page with games list`(): Unit = runBlocking {
        val response = ClientUtil.getClient().get(urlString = StagingConfig.URL)
        val htmlResponse = Jsoup.parse(response.bodyAsText())
        assertNotNull(htmlResponse.head())
        checkHead(htmlResponse.head())
        assertNotNull(htmlResponse.body())
        checkNavBar(htmlResponse.body())
        checkGames(htmlResponse.body())
    }
}
