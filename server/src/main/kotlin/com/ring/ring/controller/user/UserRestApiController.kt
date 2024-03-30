package com.ring.ring.controller.user

import com.ring.ring.usecase.user.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserRestApiController(
    private val getUser: GetUser = GetUser(),
    private val createUser: CreateUser = CreateUser(),
    private val editUser: EditUser = EditUser(),
    private val withdrawalUser: WithdrawalUser = WithdrawalUser(),
    private val login: Login = Login(),
) {
    suspend fun signUp(call: ApplicationCall) {
        try {
            val req = call.receive<CreateUser.Req>()
            createUser(req)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun get(call: ApplicationCall) {
        val req = call.receive<GetUser.Req>()
        val res = getUser(req)
        call.respond(HttpStatusCode.OK, res.user)
    }

    suspend fun edit(call: ApplicationCall) {
        val req = call.receive<EditUser.Req>()
        editUser(req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun withdrawal(call: ApplicationCall) {
        val req = call.receive<WithdrawalUser.Req>()
        withdrawalUser(req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun login(call: ApplicationCall) {
        val req = call.receive<Login.Req>()
        val res = login(req)
        call.respond(HttpStatusCode.OK, res)
    }
}