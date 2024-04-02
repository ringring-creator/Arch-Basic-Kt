package com.ring.ring.com.ring.ring.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.loginSessionRouting(
    controller: LoginSessionController = LoginSessionController()
) {
    route("/session") {
        post("login") { controller.login(call) }
    }
}