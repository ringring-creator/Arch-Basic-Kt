package com.ring.ring.session.exist

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.existSessionRouting() {
    val controller = ExistSessionController()
    route("/session") {
        post("exist") { controller.exist(call) }
    }
}