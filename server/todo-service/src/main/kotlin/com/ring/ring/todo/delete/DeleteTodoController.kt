package com.ring.ring.todo.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class DeleteTodoController(
    private val deleteTodo: DeleteTodo = DeleteTodo(),
    private val deleteTodoByUserId: DeleteTodoByUserId = DeleteTodoByUserId(),
) {
    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteTodo.Req>()
        deleteTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun deleteByUserId(call: ApplicationCall) {
        val req = call.receive<DeleteTodoByUserId.Req>()
        deleteTodoByUserId(req = req)
        call.respond(HttpStatusCode.OK)
    }
}