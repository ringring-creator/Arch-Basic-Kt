package com.ring.ring.todo.create

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.createTodoRouting(
    controller: CreateTodoController = CreateTodoController(),
) {
    route("/todo") {
        post("create") { controller.create(call) }
    }
}