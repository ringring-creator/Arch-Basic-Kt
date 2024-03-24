package com.ring.ring.router.todo

import com.ring.ring.controller.todo.TodoApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.todoApiRouting(
    controller: TodoApiController = TodoApiController(),
) {
    route("/todo/api") {
        post { controller.create(call) }
        post("edit") { controller.edit(call) }
        post("delete") { controller.delete(call) }
    }
}