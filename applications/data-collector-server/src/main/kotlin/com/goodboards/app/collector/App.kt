package com.goodboards.app.collector

import com.goodboards.app.collector.tasks.RetrieveNewsTaskHelper
import com.goodboards.workflow.WorkScheduler
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.util.*

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("hi!", ContentType.Text.Html)
        }
    }
    RetrieveNewsTaskHelper.scheduleRetrieveNewsTask()
}

fun main() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val port = System.getenv("PORT")?.toInt() ?: 8888
    embeddedServer(Netty, port, watchPaths = listOf("data-collector-server"), module = Application::module).start()
}