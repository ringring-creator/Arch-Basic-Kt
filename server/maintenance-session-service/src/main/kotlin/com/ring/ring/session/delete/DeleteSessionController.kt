package com.ring.ring.session.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class DeleteSessionController(
    private val deleteSession: DeleteSession = DeleteSession(),
) {
    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteSession.Req>()
        deleteSession(req)
        call.respond(HttpStatusCode.OK)
    }
}