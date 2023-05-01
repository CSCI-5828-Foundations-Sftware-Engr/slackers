package com.goodboards.app.collector

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.statement.*
import io.ktor.client.request.*

class HttpClientWrapper {

    private val client = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun get(url: String): HttpResponse {
        val response: HttpResponse = client.get(url)
        return response
    }
}