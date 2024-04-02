package com.ring.ring.session.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.loginSessionRouting() {
    val controller = LoginSessionController()
    route("/session") {
        post("login") { controller.login(call) }
    }
}