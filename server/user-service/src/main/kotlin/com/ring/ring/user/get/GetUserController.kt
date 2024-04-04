package com.ring.ring.user.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class GetUserController(
    private val getUser: GetUser = GetUser(),
) {
    suspend fun get(call: ApplicationCall) {
        val req = call.receive<GetUser.Req>()
        val res = getUser(req)
        call.respond(HttpStatusCode.OK, res.user)
    }
}