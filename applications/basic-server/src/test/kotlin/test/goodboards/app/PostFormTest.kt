package test.goodboards.app;

import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.server.testing.*
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PostFormTest: BaseAppTest() {

    @Test
    fun testPutFormRespondsWithName() = testApp {
        handleRequest(HttpMethod.Post, "/form") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf("title" to "ABCD").formUrlEncode())
        }.apply{
                assertEquals(200, response.status()?.value)
                assertTrue(response.content!!.contains("ABCD"))
        }
    }

    @Test
    fun testPutFormGiveSuccess() = testApp {
        handleRequest(HttpMethod.Get, "/form").apply {
            assertEquals(200, response.status()?.value)
        }
    }
}
