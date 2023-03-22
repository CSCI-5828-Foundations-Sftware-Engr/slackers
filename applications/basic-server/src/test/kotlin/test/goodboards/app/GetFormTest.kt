package test.goodboards.app

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetFormTest : BaseAppTest() {


    @Ignore
    @Test
    fun testGetFormRespondsWithPage() = testApp {
        handleRequest(HttpMethod.Get, "/form").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("What's your name?"))
        }
    }

    @Test
    fun testGetFormGiveSuccess() = testApp {
        handleRequest(HttpMethod.Get, "/form").apply {
            assertEquals(200, response.status()?.value)
        }
    }
}