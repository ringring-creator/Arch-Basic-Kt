package com.ring.ring.controller.todo

import com.ring.ring.exception.BadRequestException
import com.ring.ring.exception.NotLoggedInException
import com.ring.ring.usecase.todo.*
import com.ring.ring.util.DateUtil
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.toLocalDate

class TodoRestApiController(
    private val createTodo: CreateTodo = CreateTodo(),
    private val getTodo: GetTodo = GetTodo(),
    private val getTodoList: GetTodoList = GetTodoList(),
    private val editTodo: EditTodo = EditTodo(),
    private val deleteTodo: DeleteTodo = DeleteTodo(),
) {
    suspend fun create(call: ApplicationCall) {
        val req = call.receive<CreateTodo.Req>()
        createTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun get(call: ApplicationCall) {
        val req = call.receive<GetTodo.Req>()
        val res = getTodo(req = req)
        call.respond(HttpStatusCode.OK, res.todo)
    }

    suspend fun list(call: ApplicationCall) {
        val req = call.receive<GetTodoList.Req>()
        val res = getTodoList(req = req)
        call.respond(HttpStatusCode.OK, res.todoList)
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