package com.goodboards.tests

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.goodboards.tests.plugins.*

fun main() {
    throw RuntimeException("This is a test project and this should never be deployed.")
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
