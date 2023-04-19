package com.goodboards.tests.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.server.application.*

object ClientUtil {
    fun getClient(): HttpClient= HttpClient(CIO) {
    }
}