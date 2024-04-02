package com.ring.ring.com.ring.ring.logout

import com.ring.ring.logout.LogoutSessionController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.logoutSessionRouting(
    controller: LogoutSessionController = LogoutSessionController()
) {
    route("/session") {
        post("logout") { controller.logout(call) }
    }
}