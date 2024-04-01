package com.ring.ring.session.validate

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.validateSessionRouting(
    controller: ValidateSessionController = ValidateSessionController()
) {
    route("/session") {
        post("validate") { controller.validate(call) }
    }
}