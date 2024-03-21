package com.ring.ring.router

import com.ring.ring.router.user.apiUserRouting
import com.ring.ring.router.user.todoViewRouting
import com.ring.ring.router.user.userViewRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        apiUserRouting()
        userViewRouting()
        todoViewRouting()
    }
}