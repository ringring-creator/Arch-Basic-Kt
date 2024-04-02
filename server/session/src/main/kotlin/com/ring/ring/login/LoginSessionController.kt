package com.ring.ring.com.ring.ring.login

import com.ring.ring.login.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class LoginSessionController(
    private val login: Login = Login(),
) {
    suspend fun login(call: ApplicationCall) {
        val req = call.receive<Login.Req>()
        val res = login(req)
        call.respond(HttpStatusCode.OK, res)
    }
}