package com.ring.ring

import com.ring.ring.com.ring.ring.login.LoginFailureException
import com.ring.ring.com.ring.ring.login.loginSessionRouting
import com.ring.ring.com.ring.ring.logout.logoutSessionRouting
import com.ring.ring.com.ring.ring.validate.NotLoggedInException
import com.ring.ring.com.ring.ring.validate.validateSessionRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Application.sessionModule() {
    configureSerialization()
    configureCors()
    configureStatusPages()
    sessionConfigureRouting()
}

private fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
            ContentType.Application.Json
        )
    }
}

private fun Application.configureCors() {
    install(CORS) {
        anyHost()
        allowCredentials = true
        allowNonSimpleContentTypes = true
        allowHeaders { true }
    }
}

private fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
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

private fun Application.sessionConfigureRouting() {
    routing {
        loginSessionRouting()
        logoutSessionRouting()
        validateSessionRouting()
    }
}