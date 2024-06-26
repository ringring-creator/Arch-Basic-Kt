package com.ring.ring.router

import com.ring.ring.controller.TodoRestApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.todoRestApiRouting(
    controller: TodoRestApiController = TodoRestApiController(),
) {
    route("/todo") {
        post("create") { controller.create(call) }
        post("get") { controller.get(call) }
        post("list") { controller.list(call) }
        post("edit") { controller.edit(call) }
        post("delete") { controller.delete(call) }
        post("editDone") { controller.editDone(call) }
    }
}