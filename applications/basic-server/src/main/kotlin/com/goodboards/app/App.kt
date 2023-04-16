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
import java.io.File
import java.sql.DriverManager
import com.fasterxml.jackson.databind.ObjectMapper

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

private fun readGameJsonIntoDB(jsonFilePath: String, url: String, user: String, password: String): Boolean{
    val url = "postgres://oqvckqlhqqiscz:196dc00d1eb1a6628992e3652ad2fcce8842a56086d4b4e1cb918007c00dc338@ec2-44-215-40-87.compute-1.amazonaws.com:5432/dbasdbm3uauplc"
    val user = "oqvckqlhqqiscz"
    val password = "196dc00d1eb1a6628992e3652ad2fcce8842a56086d4b4e1cb918007c00dc338"
    val tableName = "Games"

    // Connect to the Heroku database
    DriverManager.getConnection(url, user, password).use { conn ->
        conn.createStatement().use { stmt ->

            // Open and read the JSON file
            val jsonString = File(jsonFilePath).readText()

            // Parse the JSON data
            val objectMapper = ObjectMapper()
            val jsonNode = objectMapper.readTree(jsonString)

            // Insert the JSON data into the database
            val sql = "INSERT INTO $tableName (json_data) VALUES (?)"
            val pstmt = conn.prepareStatement(sql)
            pstmt.setObject(1, jsonNode)
            pstmt.executeUpdate()

            // Close the prepared statement
            pstmt.close()
        }
    }

    return true;
}

fun main() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val port = System.getenv("PORT")?.toInt() ?: 8888
    readGameJsonIntoDB("game_info.json");
    embeddedServer(Netty, port, watchPaths = listOf("basic-server"), module = { module() }).start()
}
