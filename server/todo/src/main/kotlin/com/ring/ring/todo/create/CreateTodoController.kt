package com.ring.ring.todo.create

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class CreateTodoController(
    private val createTodo: CreateTodo = CreateTodo(),
) {
    suspend fun create(call: ApplicationCall) {
        val req = call.receive<CreateTodo.Req>()
        createTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }
}