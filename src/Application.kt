package com.practice

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {

        get("/") {
            call.respondText("Hello World")
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]
            call.respondText("Todolist Details for Todo Item #$id")
        }
    }
}

