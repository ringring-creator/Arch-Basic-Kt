package com.ring.ring.user.get

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getUserRouting() {
    val controller = GetUserController()
    route("/user") {
        post("get") { controller.get(call) }
    }
}