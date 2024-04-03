package com.ring.ring.session.exist

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class ExistSessionController(
    private val existSession: ExistSession = ExistSession(),
) {
    suspend fun exist(call: ApplicationCall) {
        val req = call.receive<ExistSession.Req>()
        existSession(req)
        call.respond(HttpStatusCode.OK)
    }
}