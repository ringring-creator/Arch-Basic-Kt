package com.ring.ring.todo.edit

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class EditTodoController(
    private val editTodo: EditTodo = EditTodo(),
    private val editTodoDone: EditTodoDone = EditTodoDone(),
) {
    suspend fun edit(call: ApplicationCall) {
        val req = call.receive<EditTodo.Req>()
        editTodo(req = req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun editDone(call: ApplicationCall) {
        val req = call.receive<EditTodoDone.Req>()
        editTodoDone(req = req)
        call.respond(HttpStatusCode.OK)
    }
}