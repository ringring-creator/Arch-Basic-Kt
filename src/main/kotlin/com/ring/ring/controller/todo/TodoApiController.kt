package com.ring.ring.controller.todo

import com.ring.ring.exception.BadRequestException
import com.ring.ring.exception.NotLoggedInException
import com.ring.ring.usecase.todo.CreateTodo
import com.ring.ring.usecase.todo.DeleteTodo
import com.ring.ring.usecase.todo.EditTodo
import com.ring.ring.util.DateUtil
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.toLocalDate

class TodoApiController(
    private val createTodo: CreateTodo = CreateTodo(),
    private val editTodo: EditTodo = EditTodo(),
    private val deleteTodo: DeleteTodo = DeleteTodo(),
) {
    suspend fun create(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        createTodo(req = convertCreateTodoReq(parameters))
        call.respondRedirect("/todo/list")
    }

    suspend fun edit(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        editTodo(req = convertEditTodoReq(parameters))
        call.respondRedirect("/todo/list")
    }

    suspend fun delete(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        deleteTodo(req = convertDeleteTodoReq(parameters))
        call.respondRedirect("/todo/list")
    }

    private fun convertCreateTodoReq(parameters: Parameters): CreateTodo.Req = CreateTodo.Req(
        title = parameters["title"] ?: "",
        description = parameters["description"] ?: "",
        done = parameters["done"].toBoolean(),
        deadline = parameters["deadline"]?.toLocalDate() ?: DateUtil.currentLocalDate(),
        userId = parameters["userId"]?.toLong() ?: throw NotLoggedInException(message = "User not logged in."),
    )

    private fun convertEditTodoReq(parameters: Parameters): EditTodo.Req = EditTodo.Req(
        id = parameters["id"] ?: "",
        title = parameters["title"] ?: "",
        description = parameters["description"] ?: "",
        done = parameters["done"].toBoolean(),
        deadline = parameters["deadline"]?.toLocalDate() ?: DateUtil.currentLocalDate(),
        userId = parameters["userId"]?.toLong() ?: throw NotLoggedInException(message = "User not logged in."),
    )

    private fun convertDeleteTodoReq(parameters: Parameters): DeleteTodo.Req = DeleteTodo.Req(
        id = parameters["id"] ?: throw BadRequestException(message = "Id is not found."),
    )

}