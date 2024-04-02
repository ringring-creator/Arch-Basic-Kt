package com.ring.ring.todo.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DeleteTodoController(
    private val deleteTodo: DeleteTodo = DeleteTodo(),
) {
    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteTodo.Req>()
        deleteTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }
}