package com.ring.ring.controller

import com.ring.ring.usecase.user.EditUser
import com.ring.ring.usecase.user.GetUser
import com.ring.ring.usecase.user.SignUp
import com.ring.ring.usecase.user.WithdrawalUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

interface UserController {
    suspend fun signUp(call: ApplicationCall)
    suspend fun get(call: ApplicationCall)
    suspend fun edit(call: ApplicationCall)
    suspend fun withdrawal(call: ApplicationCall)
}

class UserRestApiController(
    private val getUser: GetUser = GetUser(),
    private val signUp: SignUp = SignUp(),
    private val editUser: EditUser = EditUser(),
    private val withdrawalUser: WithdrawalUser = WithdrawalUser(),
) : UserController {
    override suspend fun signUp(call: ApplicationCall) {
        try {
            val req = call.receive<SignUp.Req>()
            signUp(req)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    override suspend fun get(call: ApplicationCall) {
        val req = call.receive<GetUser.Req>()
        val res = getUser(req)
        call.respond(HttpStatusCode.OK, res.user)
    }

    override suspend fun edit(call: ApplicationCall) {
        val req = call.receive<EditUser.Req>()
        editUser(req)
        call.respond(HttpStatusCode.OK)
    }

    override suspend fun withdrawal(call: ApplicationCall) {
        val req = call.receive<WithdrawalUser.Req>()
        withdrawalUser(req)
        call.respond(HttpStatusCode.OK)
    }
}