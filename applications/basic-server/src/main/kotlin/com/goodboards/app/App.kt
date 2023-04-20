package com.goodboards.app

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import models.Game
import java.util.*

val games = mutableListOf(
    Game.newEntry("Uno", "typical friendship destroying game"),
    Game.newEntry("Uno", "typical friendship destroying game"),
    Game.newEntry("Uno", "typical friendship destroying game"),
    Game.newEntry("Uno", "typical friendship destroying game"),
    Game.newEntry("Uno", "typical friendship destroying game"),
    Game.newEntry("Uno", "typical friendship destroying game"),
)

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Routing) {
        get {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to games)))
        }
        get("/news") {
            call.respond(FreeMarkerContent("news.ftl", mapOf("games" to games)))
        }
        get("/contact") {
            call.respond(FreeMarkerContent("contact.ftl", mapOf("games" to games)))
        }
        get("/games") {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to games)))
        }

        static("images") { resources("images") }
        static("style") { resources("style") }
    }
}

private fun PipelineContext<Unit, ApplicationCall>.headers(): MutableMap<String, String> {
    val headers = mutableMapOf<String, String>()
    call.request.headers.entries().forEach { entry ->
        headers[entry.key] = entry.value.joinToString()
    }
    return headers
}

fun main() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val port = System.getenv("PORT")?.toInt() ?: 8888
    embeddedServer(Netty, port, watchPaths = listOf("basic-server"), module = { module() }).start()
}
