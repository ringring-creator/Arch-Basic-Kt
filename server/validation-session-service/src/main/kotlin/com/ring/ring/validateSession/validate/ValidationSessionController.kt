package com.ring.ring.validateSession.validate

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class ValidationSessionController(
    private val validateSession: ValidateSession = ValidateSession(),
) {
    suspend fun validate(call: ApplicationCall) {
        val req = call.receive<ValidateSession.ReqSession>()
        val res = validateSession(req)
        call.respond(HttpStatusCode.OK, res)
    }
}