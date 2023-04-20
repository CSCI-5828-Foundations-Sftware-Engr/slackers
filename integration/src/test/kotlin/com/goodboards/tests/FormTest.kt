package com.goodboards.tests

import com.goodboards.tests.client.ClientUtil
import io.ktor.client.statement.*
import kotlin.test.Test
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class FormTest {

    companion object {
        val STAGING_URL: String = "https://slackers-csci-5828-staging-v2.herokuapp.com/form"
    }

    @Test
    fun `get form returns page with form`() = runBlocking {
        val response = ClientUtil.getClient().get(urlString = STAGING_URL)
        val htmlResponse = Jsoup.parse(response.bodyAsText())
        assertNotNull(htmlResponse.body())
        // There is a label
        assertTrue(htmlResponse.body().select("label").isNotEmpty())
        // The label has the required text
        assertTrue(htmlResponse.body().select("label").text().equals("What is your name?"))
        // There is an input text box
        assertTrue(htmlResponse.body().select("input[type=text]").isNotEmpty())
        // There is a input submit element
        assertTrue(htmlResponse.body().select("input[type=submit]").isNotEmpty())
    }

}
