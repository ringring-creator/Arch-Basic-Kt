package com.ring.ring.router.todo

import com.ring.ring.usecase.todo.CreateTodo
import com.ring.ring.usecase.todo.DeleteTodo
import com.ring.ring.usecase.todo.EditTodo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.datetime.*

fun Route.todoApiRouting() {
    route("/todo/api") {
        post {
            val parameters = call.receiveParameters()
            val createTodo = CreateTodo()
            createTodo(req = convertCreateTodoReq(parameters))
            call.respondRedirect("/todo/list")
        }
        post("edit") {
            val parameters = call.receiveParameters()
            val editTodo = EditTodo()
            editTodo(req = convertEditTodoReq(parameters))
            call.respondRedirect("/todo/list")
        }
        post("delete") {
            val parameters = call.receiveParameters()
            val deleteTodo = DeleteTodo()
            deleteTodo(req = convertDeleteTodoReq(parameters))
            call.respondRedirect("/todo/list")
        }
    }
}

private fun convertCreateTodoReq(parameters: Parameters): CreateTodo.Req = CreateTodo.Req(
    title = parameters["title"] ?: "",
    description = parameters["description"] ?: "",
    done = parameters["done"].toBoolean(),
    deadline = parameters["deadline"]?.toLocalDate() ?: currentLocalDate(),
    userId = parameters.getOrFail("userId").toLong(),
)

private fun convertEditTodoReq(parameters: Parameters): EditTodo.Req = EditTodo.Req(
    id = parameters["id"] ?: "",
    title = parameters["title"] ?: "",
    description = parameters["description"] ?: "",
    done = parameters["done"].toBoolean(),
    deadline = parameters["deadline"]?.toLocalDate() ?: currentLocalDate(),
    userId = parameters.getOrFail("userId").toLong(),
)

fun convertDeleteTodoReq(parameters: Parameters): DeleteTodo.Req = DeleteTodo.Req(
    id = parameters["id"] ?: "",
)

private fun currentLocalDate(): LocalDate = Clock.System.now()
    .toLocalDateTime(
        TimeZone.currentSystemDefault()
    ).date