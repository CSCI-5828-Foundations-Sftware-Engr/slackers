package test.goodboards.app;

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PostFormTest: BaseAppTest() {


    @Test
    fun testPutFormRespondsWithName() = testApp {
        val post_param_name : String = "title"
        val post_param_value : String = "ABCD"
        handleRequest(HttpMethod.Post, "/form") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf(post_param_name to post_param_value).formUrlEncode())
        }.apply{
                assertEquals(200, response.status()?.value)
                assertTrue(response.content!!.contains(post_param_value))
        }
    }

    @Test
    fun testPutFormGiveSuccess() = testApp {
        handleRequest(HttpMethod.Get, "/form").apply {
            assertEquals(200, response.status()?.value)
        }
    }
}
