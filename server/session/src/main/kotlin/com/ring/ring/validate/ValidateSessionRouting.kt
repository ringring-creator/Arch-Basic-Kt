package com.ring.ring.com.ring.ring.validate

import com.ring.ring.validate.ValidateSessionController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.validateSessionRouting(
    controller: ValidateSessionController = ValidateSessionController()
) {
    route("/session") {
        post("validate") { controller.validate(call) }
    }
}