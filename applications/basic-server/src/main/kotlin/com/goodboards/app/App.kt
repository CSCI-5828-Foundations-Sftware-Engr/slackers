package com.goodboards.app

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import java.util.*
import models.User

val users = mutableListOf(
    User.newEntry(
        "Abhishek Purushothama",
    ),
    User.newEntry(
        "Khaled Hossain",
    ),
    User.newEntry(
        "Ch Mohammad Ibrahim",
    ),
    User.newEntry(
        "Michelle Tran",
    ),
    User.newEntry(
        "Tuan Tran",
    ),
    User.newEntry(
        "Lin Shi",
    ))

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Routing) {
        get {
            call.respond(FreeMarkerContent("index.ftl", mapOf("users" to users)))
        }
        get("/form/new") {
            call.respond(FreeMarkerContent("new.ftl", model = null))
        }
        post("/form") {
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            val newEntry = User.newEntry(title)
            users.add(newEntry)
            call.respondRedirect("/form/${newEntry.id}")
        }
        get("/form/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(FreeMarkerContent("show.ftl", mapOf("user" to users.find { it.id == id })))
        }

        post("form/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val formParameters = call.receiveParameters()
            when (formParameters.getOrFail("_action")) {
                "update" -> {
                    val index = users.indexOf(users.find { it.id == id })
                    val title = formParameters.getOrFail("title")
                    users[index].title = title
                    call.respondRedirect("/form/$id")
                }
                "delete" -> {
                    users.removeIf { it.id == id }
                    call.respondRedirect("/form")
                }
            }
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
