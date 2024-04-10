package com.ring.ring

import com.ring.ring.plugin.configureCors
import com.ring.ring.plugin.configureSerialization
import com.ring.ring.plugin.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureCors()
    configureStatusPages()
    configureRouting()
}