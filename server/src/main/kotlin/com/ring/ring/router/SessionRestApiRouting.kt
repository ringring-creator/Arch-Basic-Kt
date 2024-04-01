package com.ring.ring.router

import com.ring.ring.controller.SessionRestApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.sessionRestApiRouting(
    controller: SessionRestApiController = SessionRestApiController()
) {
    route("/session") {
        post("login") { controller.login(call) }
        post("logout") { controller.logout(call) }
    }
}