package com.ring.ring.todo.delete

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteTodoRouting(
    controller: DeleteTodoController = DeleteTodoController(),
) {
    route("/todo") {
        post("delete") { controller.delete(call) }
    }
}