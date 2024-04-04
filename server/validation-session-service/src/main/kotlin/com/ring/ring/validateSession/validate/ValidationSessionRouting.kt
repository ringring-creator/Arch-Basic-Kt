package com.ring.ring.validateSession.validate

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.validationSessionRouting() {
    val controller = ValidationSessionController()
    route("/session") {
        post("validate") { controller.validate(call) }
    }
}