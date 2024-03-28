package com.ring.ring.router

import com.ring.ring.router.user.userRestApiRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRestApiRouting()
//        userApiRouting()
//        userViewRouting()
//        todoApiRouting()
//        todoViewRouting()
    }
}