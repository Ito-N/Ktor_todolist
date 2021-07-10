package com.practice

import com.practice.entities.ToDo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {

        val todos = listOf<ToDo>(
            ToDo(1, "Plan content for video #2", true),
            ToDo(2, "Record video #2", true),
            ToDo(3, "Upload video #2", true)
        )

        get("/") {
            call.respondText("Hello World")
        }

        get("/todos") {
            call.respond(todos)
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]
            call.respondText("Todolist Details for Todo Item #$id")
        }
    }
}

