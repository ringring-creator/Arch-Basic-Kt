package com.ring.ring.plugin

import com.ring.ring.router.todo.todoApiRouting
import com.ring.ring.router.todo.todoViewRouting
import com.ring.ring.router.user.userApiRouting
import com.ring.ring.router.user.userViewRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userApiRouting()
        userViewRouting()
        todoApiRouting()
        todoViewRouting()
    }
}