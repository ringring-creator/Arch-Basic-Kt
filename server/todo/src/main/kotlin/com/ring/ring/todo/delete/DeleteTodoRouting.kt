package com.ring.ring.com.ring.ring.todo.delete

import com.ring.ring.todo.delete.DeleteTodoController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteTodoRouting(
    controller: DeleteTodoController = DeleteTodoController(),
) {
    route("/todo") {
        post("delete") { controller.delete(call) }
    }
}