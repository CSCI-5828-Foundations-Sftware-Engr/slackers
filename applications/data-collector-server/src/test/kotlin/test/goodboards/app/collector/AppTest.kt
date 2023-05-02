package test.goodboards.app.collector

import com.goodboards.app.collector.module
import com.goodboards.app.collector.tasks.RetrieveNewsTaskHelper
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class AppTest {

    @Test
    fun testEmptyHome() = testApp {
        handleRequest(HttpMethod.Get, "/").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("hi!"))
        }
    }

    private fun testApp(callback: TestApplicationEngine.() -> Unit) {
        withTestApplication({
            mockkObject(RetrieveNewsTaskHelper)
            every { RetrieveNewsTaskHelper.scheduleRetrieveNewsTask() } returns Unit
            module()
        }) { callback() }
    }
}