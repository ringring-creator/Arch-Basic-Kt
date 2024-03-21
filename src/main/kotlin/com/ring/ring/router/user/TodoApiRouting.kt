package com.ring.ring.router.user

import com.ring.ring.usecase.todo.CreateTodo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.datetime.*

fun Route.apiTodoRouting() {
    route("/todo/api") {
        get("{id}") {

        }
        post {
            val parameters = call.receiveParameters()
            val createTodo = CreateTodo()
            createTodo(req = convertCreateTodoReq(parameters))
            call.respondRedirect("/todo/list")
        }
        put {

        }
        delete("{id}") {

        }
    }
}

private fun convertCreateTodoReq(parameters: Parameters): CreateTodo.Req {
    return CreateTodo.Req(
        title = parameters["title"] ?: "",
        description = parameters["description"] ?: "",
        done = parameters["done"].toBoolean(),
        deadline = parameters["deadline"]?.toLocalDate() ?: currentLocalDate(),
        userId = parameters.getOrFail("userId").toLong(),
    )
}

private fun currentLocalDate(): LocalDate = Clock.System.now()
    .toLocalDateTime(
        TimeZone.currentSystemDefault()
    ).date