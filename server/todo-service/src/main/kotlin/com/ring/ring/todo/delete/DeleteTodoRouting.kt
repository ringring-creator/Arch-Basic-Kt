package com.ring.ring.todo.delete

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteTodoRouting() {
    val controller = DeleteTodoController()
    route("/todo") {
        post("delete") { controller.delete(call) }
        post("delete-all") { controller.deleteByUserId(call) }
    }
}