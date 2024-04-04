package com.ring.ring.validateSession.delete

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.deleteSessionRouting() {
    val controller = DeleteCacheController()
    route("/session") {
        post("delete") { controller.delete(call) }
    }
    route("/session") {
        post("delete") { controller.deleteById(call) }
    }
}