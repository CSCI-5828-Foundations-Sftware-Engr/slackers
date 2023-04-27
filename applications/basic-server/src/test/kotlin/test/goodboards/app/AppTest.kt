package test.goodboards.app

import com.goodboards.app.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class AppTest : BaseAppTest() {

    @Test
    fun testHomePage() = testApp {
        handleRequest(HttpMethod.Get, "/").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("GOODBOARDS"))
        }
    }

    @Test
    fun testContactPage() = testApp {
        handleRequest(HttpMethod.Get, "/").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("Contact"))
        }
    }
}
