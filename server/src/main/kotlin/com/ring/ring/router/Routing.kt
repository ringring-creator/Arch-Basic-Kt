package com.ring.ring.router

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRestApiRouting()
        todoRestApiRouting()
    }
}