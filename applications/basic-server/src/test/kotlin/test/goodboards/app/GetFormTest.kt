package test.goodboards.app

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class GetFormTest : BaseAppTest() {

    @Test
    fun testGetData() = testApp {
        handleRequest(HttpMethod.Get, "/").apply {
            val content = response.content!!;
            val expectedNumberOfGames = 2;
            val pattern  = "(Uno)".toRegex()
            val found = pattern.find(content)!!.groupValues
            assertEquals(found.size, expectedNumberOfGames)
        }
    }


}
