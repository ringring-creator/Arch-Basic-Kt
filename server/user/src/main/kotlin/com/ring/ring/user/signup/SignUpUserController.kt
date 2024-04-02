package com.ring.ring.user.signup

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class SignUpUserController(
    private val signUp: SignUp = SignUp(),
) {
    suspend fun signUp(call: ApplicationCall) {
        try {
            val req = call.receive<SignUp.Req>()
            signUp(req)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}