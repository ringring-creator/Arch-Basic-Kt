package com.ring.ring.com.ring.ring.todo.edit

import com.ring.ring.todo.edit.EditTodoController
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