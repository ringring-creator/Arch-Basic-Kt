package com.ring.ring.user.edit

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.editUserRouting(
    controller: EditUserController = EditUserController()
) {
    route("/user") {
        post("edit") { controller.edit(call) }
    }
}