package com.ring.ring.todo.edit

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.editTodoRouting(
    controller: EditTodoController = EditTodoController(),
) {
    route("/todo") {
        post("edit") { controller.edit(call) }
        post("editDone") { controller.editDone(call) }
    }
}