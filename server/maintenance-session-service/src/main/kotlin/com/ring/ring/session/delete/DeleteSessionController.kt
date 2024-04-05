package com.ring.ring.session.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class DeleteSessionController(
    private val deleteSession: DeleteSessionByUserId = DeleteSessionByUserId(),
) {
    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteSessionByUserId.Req>()
        deleteSession(req)
        call.respond(HttpStatusCode.OK)
    }
}