package com.ring.ring.todo.delete

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteTodoRouting() {
    val controller = DeleteTodoController()
    route("/todo") {
        post("delete") { controller.delete(call) }
    }
    route("/todo") {
        post("delete") { controller.deleteByUserId(call) }
    }
}