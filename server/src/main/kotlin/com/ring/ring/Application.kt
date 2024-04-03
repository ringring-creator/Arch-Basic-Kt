package com.ring.ring

import com.ring.ring.session.maintenanceSessionModule
import com.ring.ring.session.validationSessionModule
import com.ring.ring.todo.todoModule
import com.ring.ring.user.userModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::userModule)
        .start(wait = false)
    embeddedServer(Netty, port = 8082, host = "0.0.0.0", module = Application::maintenanceSessionModule)
        .start(wait = false)
    embeddedServer(Netty, port = 8084, host = "0.0.0.0", module = Application::validationSessionModule)
        .start(wait = false)
    embeddedServer(Netty, port = 8083, host = "0.0.0.0", module = Application::todoModule)
        .start(wait = true)
}