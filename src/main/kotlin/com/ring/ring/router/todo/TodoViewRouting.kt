package com.ring.ring.router.todo

import com.ring.ring.controller.todo.TodoController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.todoViewRouting(
    controller: TodoController = TodoController()
) {
    route("/todo") {
        get("list") { controller.list(call) }
        get { controller.get(call) }
        get("create") { controller.create(call) }
        get("edit") { controller.edit(call) }
        get("delete") { controller.delete(call) }
    }
}