package com.ring.ring.controller.todo

import com.ring.ring.usecase.todo.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

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
        val req = call.receive<EditTodo.Req>()
        editTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteTodo.Req>()
        deleteTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }
}