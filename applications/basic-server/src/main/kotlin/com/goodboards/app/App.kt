package com.goodboards.app

import com.goodboards.app.database.DatabaseInit
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import com.goodboards.app.game.Game
import com.goodboards.app.game.GamesHelper
import java.util.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*


//val games = mutableListOf(
//    Game("Uno", "typical friendship destroying game"),
//    Game("Uno", "typical friendship destroying game"),
//    Game("Uno", "typical friendship destroying game"),
//    Game("Uno", "typical friendship destroying game"),
//    Game("Uno", "typical friendship destroying game"),
//    Game("Uno", "typical friendship destroying game"),
//)

private val logger = LoggerFactory.getLogger("App.kt")
val client = HttpClient(CIO) {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Routing) {
        get {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to GamesHelper.getAllGames())))
        }
        get("/contact") {
            call.respond(FreeMarkerContent("contact.ftl", mapOf("games" to GamesHelper.getAllGames())))
        }

        get("/game/{id}") {
            val id = call.parameters.getOrFail<String>("id")
//            val news: NewsResponse = client.get("https://newsapi.org/v2/everything?q=board_games&apiKey=18af37ae1b52421d808c96babcf7db7b")

            call.respond(FreeMarkerContent("game.ftl", mapOf("game" to GamesHelper.getAllGames().find { it.id == id })))
        }
        get("/game/{id}/new") {
            val id = call.parameters.getOrFail<String>("id")
            call.respond(FreeMarkerContent("newGame.ftl", mapOf("game" to GamesHelper.getAllGames().find { it.id == id })))
        }
        get("/contact") {
            call.respond(FreeMarkerContent("contact.ftl", mapOf("games" to GamesHelper.getAllGames())))
        }
        get("/games") {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to GamesHelper.getAllGames())))
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
    try {
        DatabaseInit.readGameJsonIntoDB("game_info.json");
    } catch (e: Exception) {
        logger.error("Error reading game JSON, $e")
    }
    embeddedServer(Netty, port, watchPaths = listOf("basic-server"), module = { module() }).start()
}