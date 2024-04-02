package com.ring.ring.com.ring.ring.get

import com.ring.ring.user.get.GetUserController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getUserRouting(
    controller: GetUserController = GetUserController()
) {
    route("/user") {
        post("get") { controller.get(call) }
    }
}