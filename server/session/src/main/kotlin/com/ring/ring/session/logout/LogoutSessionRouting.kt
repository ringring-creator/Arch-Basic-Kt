package com.ring.ring.session.logout

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.logoutSessionRouting() {
    val controller = LogoutSessionController()
    route("/session") {
        post("logout") { controller.logout(call) }
    }
}