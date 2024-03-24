package com.ring.ring.controller.todo

import com.ring.ring.ui.todo.*
import com.ring.ring.usecase.todo.GetTodo
import com.ring.ring.usecase.todo.GetTodoList
import com.ring.ring.usecase.user.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.sessions.*

class TodoController(
    private val getTodoList: GetTodoList = GetTodoList(),
    private val getTodo: GetTodo = GetTodo(),
) {
    suspend fun list(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        val res = getTodoList(GetTodoList.Req(session!!.userId))
        call.respondHtml(HttpStatusCode.OK) {
            todoListView(res)
        }
    }

    suspend fun get(call: ApplicationCall) {
        val id = call.parameters["id"]?.toLong() ?: 0L
        val todo = getTodo(req = convertGetTodoReq(id = id))
        call.respondHtml(HttpStatusCode.OK) {
            todoView(todo)
        }
    }

    suspend fun create(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        call.respondHtml(HttpStatusCode.OK) {
            createTodoView(session!!.userId)
        }
    }

    suspend fun edit(call: ApplicationCall) {
        val id = call.parameters["id"]?.toLong() ?: 0L
        val todo = getTodo(req = convertGetTodoReq(id = id))
        call.respondHtml(HttpStatusCode.OK) {
            editTodoView(todo)
        }
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"]?.toLong() ?: 0L
        val todo = getTodo(req = convertGetTodoReq(id = id))
        call.respondHtml(HttpStatusCode.OK) {
            deleteTodoView(todo)
        }
    }

    private fun convertGetTodoReq(id: Long): GetTodo.Req = GetTodo.Req(id = id)
}