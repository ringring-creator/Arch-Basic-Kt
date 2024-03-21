package com.ring.ring

import com.ring.ring.router.configureRouting
import com.ring.ring.usecase.user.Login
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.sessions.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSession()
    configureRouting()
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
    }
}

fun Application.configureSession() {
    install(Sessions) {
        cookie<Login.Res.Session>("SESSION")
    }
}
