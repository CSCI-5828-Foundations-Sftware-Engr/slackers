package test.goodboards.app;

import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PutFormTest: BaseAppTest() {

    @Test
    fun testRespondsWithName() = testApp {
        handleRequest(HttpMethod.Put, "/form", {
            setBody("goodboards")
        }
            ).apply{
                assertEquals(200, response.status()?.value)
                assertTrue(response.content!!.contains("goodboards"))
        }

    }
}
