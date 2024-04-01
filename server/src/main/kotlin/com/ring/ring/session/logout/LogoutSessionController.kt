package com.ring.ring.session.logout

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class LogoutSessionController(
    private val logout: Logout = Logout(),
) {
    suspend fun logout(call: ApplicationCall) {
        val req = call.receive<Logout.Req>()
        logout(req)
        call.respond(HttpStatusCode.OK)
    }
}