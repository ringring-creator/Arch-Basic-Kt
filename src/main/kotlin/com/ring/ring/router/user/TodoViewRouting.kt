package com.ring.ring.router.user

import com.ring.ring.ui.todo.createTodoView
import com.ring.ring.ui.todo.editTodoView
import com.ring.ring.ui.todo.todoListView
import com.ring.ring.ui.todo.todoView
import com.ring.ring.usecase.todo.GetTodo
import com.ring.ring.usecase.todo.GetTodoList
import com.ring.ring.usecase.user.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.todoViewRouting() {
    route("/todo") {
        get {
            val id = call.parameters["id"]?.toLong() ?: 0L
            val getTodo = GetTodo()
            val todo = getTodo(req = convertGetTodoReq(id = id))
            call.respondHtml(HttpStatusCode.OK) {
                todoView(todo)
            }
        }
        get("list") {
            val session = call.sessions.get<Login.Res.Session>()
            val getTodoList = GetTodoList()
            val res = getTodoList(GetTodoList.Req(session!!.userId))
            call.respondHtml(HttpStatusCode.OK) {
                todoListView(res)
            }
        }
        get("create") {
            val session = call.sessions.get<Login.Res.Session>()
            call.respondHtml(HttpStatusCode.OK) {
                createTodoView(session!!.userId)
            }
        }
        get("edit") {
            val id = call.parameters["id"]?.toLong() ?: 0L
            val getTodo = GetTodo()
            val todo = getTodo(req = convertGetTodoReq(id = id))
            call.respondHtml(HttpStatusCode.OK) {
                editTodoView(todo)
            }
        }
        get("delete") {
            val id = call.parameters["id"]?.toLong() ?: 0L
            call.respondHtml(HttpStatusCode.OK) {
//                editTodoView()
            }
        }
    }
}

private fun convertGetTodoReq(id: Long): GetTodo.Req = GetTodo.Req(id = id)