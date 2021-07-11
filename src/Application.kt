package com.practice

import com.practice.repository.InMemoryToDoRepository
import com.practice.repository.ToDoRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
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

        // 型変換(ここでは、派生クラス→基底クラスのアップキャスト)
        val repository: ToDoRepository = InMemoryToDoRepository()

        get("/") {
            call.respondText("Hello World")
        }

        get("/todos") {
            call.respond(repository.getAllToDos())
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest,
                "id prameter has to be a number"
                )
                return@get
            }

            val todo = repository.getToDo(id)

            if (todo == null) {
                call.respond(HttpStatusCode.NotFound,
                "found no todo for the provided id $id"
                )
            } else {
                call.respond(todo)
            }
        }
    }
}

