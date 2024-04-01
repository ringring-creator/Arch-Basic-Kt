package com.ring.ring.todo.get

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getTodoRouting(
    controller: GetTodoController = GetTodoController(),
) {
    route("/todo") {
        post("get") { controller.get(call) }
    }
}