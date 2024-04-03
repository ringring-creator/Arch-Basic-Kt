package com.ring.ring.session.delete

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteSessionRouting() {
    val controller = DeleteSessionController()
    route("/session") {
        post("delete") { controller.logout(call) }
    }
}