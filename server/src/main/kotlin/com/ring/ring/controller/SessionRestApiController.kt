package com.ring.ring.controller

import com.ring.ring.usecase.session.Login
import com.ring.ring.usecase.session.Logout
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class SessionRestApiController(
    private val login: Login = Login(),
    private val logout: Logout = Logout(),
) {
    suspend fun login(call: ApplicationCall) {
        val req = call.receive<Login.Req>()
        val res = login(req)
        call.respond(HttpStatusCode.OK, res)
    }

    suspend fun logout(call: ApplicationCall) {
        val req = call.receive<Logout.Req>()
        logout(req)
        call.respond(HttpStatusCode.OK)
    }
}