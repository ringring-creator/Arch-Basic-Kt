package com.ring.ring

import com.ring.ring.plugin.configureSerialization
import com.ring.ring.plugin.configureSession
import com.ring.ring.router.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSession()
    install(CORS) {
        anyHost()
        allowCredentials = true
        allowNonSimpleContentTypes = true
        allowHeaders { true }
    }

//    configureStatusPages()
    configureRouting()
}