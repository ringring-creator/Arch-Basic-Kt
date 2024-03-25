package com.ring.ring.plugin

import com.ring.ring.exception.BadRequestException
import com.ring.ring.exception.LoginFailureException
import com.ring.ring.exception.NotLoggedInException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is BadRequestException -> {
                    call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                }

                is LoginFailureException -> {
                    call.respondText(text = "401: ${cause.message}", status = HttpStatusCode.Unauthorized)
                }

                is NotLoggedInException -> {
                    call.respondText(text = "403: ${cause.message}", status = HttpStatusCode.Forbidden)
                }

                else -> {
                    call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
