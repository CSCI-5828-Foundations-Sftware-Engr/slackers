package test.goodboards.app

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SingleGameTest : BaseAppTest() {
    @Test
    fun testSingleGameResponse() = testApp {
        handleRequest(HttpMethod.Get, "/game/0").apply {
            assertEquals(200, response.status()?.value)
        }
    }

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