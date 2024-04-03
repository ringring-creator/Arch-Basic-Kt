package com.ring.ring.todo.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class GetTodoController(
    private val getTodo: GetTodo = GetTodo(),
) {
    suspend fun get(call: ApplicationCall) {
        val req = call.receive<GetTodo.Req>()
        val res = getTodo(req = req)
        call.respond(HttpStatusCode.OK, res.todo)
    }
}