package com.ring.ring.user.exist

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class ExistUserController(
    private val existUser: ExistUser = ExistUser(),
) {
    suspend fun get(call: ApplicationCall) {
        val req = call.receive<ExistUser.Req>()
        val res = existUser(req)
        call.respond(HttpStatusCode.OK, res)
    }
}