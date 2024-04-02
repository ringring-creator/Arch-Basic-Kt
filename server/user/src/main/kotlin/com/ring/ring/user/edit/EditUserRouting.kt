package com.ring.ring.com.ring.ring.edit

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.editUserRouting(
    controller: EditUserController = EditUserController()
) {
    route("/user") {
        post("edit") { controller.edit(call) }
    }
}