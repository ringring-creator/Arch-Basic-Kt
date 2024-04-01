package com.ring.ring.session.logout

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.logoutSessionRouting(
    controller: LogoutSessionController = LogoutSessionController()
) {
    route("/session") {
        post("logout") { controller.logout(call) }
    }
}