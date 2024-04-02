package com.ring.ring.todo.list

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class ListTodoController(
    private val getTodoList: GetTodoList = GetTodoList(),
) {
    suspend fun list(call: ApplicationCall) {
        val req = call.receive<GetTodoList.Req>()
        val res = getTodoList(req = req)
        call.respond(HttpStatusCode.OK, res.todoList)
    }
}