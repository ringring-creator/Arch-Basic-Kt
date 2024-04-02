package com.ring.ring.user.edit

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.editUserRouting() {
    val controller = EditUserController()
    route("/user") {
        post("edit") { controller.edit(call) }
    }
}