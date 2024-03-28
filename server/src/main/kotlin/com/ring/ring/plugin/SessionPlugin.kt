package com.ring.ring.plugin

import com.ring.ring.usecase.user.Login
import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSession() {
    install(Sessions) {
        cookie<Login.Res.Session>("SESSION")
    }
}
