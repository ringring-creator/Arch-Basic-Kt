package com.ring.ring.router.todo

import com.ring.ring.controller.todo.TodoRestApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.todoRestApiRouting(
    controller: TodoRestApiController = TodoRestApiController(),
) {
    route("/todo") {
        post("create") { controller.create(call) }
        post("edit") { controller.edit(call) }
        post("delete") { controller.delete(call) }
    }
}