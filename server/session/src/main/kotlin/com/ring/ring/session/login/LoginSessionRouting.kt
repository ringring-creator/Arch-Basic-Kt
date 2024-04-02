package com.ring.ring.com.ring.ring.login

import com.ring.ring.session.login.LoginSessionController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.loginSessionRouting(
    controller: LoginSessionController = LoginSessionController()
) {
    route("/session") {
        post("login") { controller.login(call) }
    }
}