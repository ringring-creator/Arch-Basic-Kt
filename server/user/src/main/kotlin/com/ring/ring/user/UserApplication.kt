package com.ring.ring.user

import com.ring.ring.com.ring.ring.edit.editUserRouting
import com.ring.ring.com.ring.ring.get.getUserRouting
import com.ring.ring.com.ring.ring.signup.signUpUserRestApiRouting
import com.ring.ring.user.withdrawal.withdrawalUserRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Application.userModule() {
    configureSerialization()
    configureCors()
    configureStatusPages()
    userConfigureRouting()
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
            call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
    }
}

private fun Application.userConfigureRouting() {
    routing {
        editUserRouting()
        getUserRouting()
        signUpUserRestApiRouting()
        withdrawalUserRouting()
    }
}