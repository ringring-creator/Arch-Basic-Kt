package com.ring.ring.user.exist

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.existUserRouting(
    controller: ExistUserController = ExistUserController()
) {
    route("/user") {
        post("exist") { controller.get(call) }
    }
}