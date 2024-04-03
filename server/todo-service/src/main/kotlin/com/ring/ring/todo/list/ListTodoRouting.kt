package com.ring.ring.todo.list

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.listTodoRouting() {
    val controller = ListTodoController()
    route("/todo") {
        post("list") { controller.list(call) }
    }
}