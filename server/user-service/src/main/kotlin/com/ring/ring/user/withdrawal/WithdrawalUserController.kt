package com.ring.ring.user.withdrawal

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class WithdrawalUserController(
    private val withdrawalUser: WithdrawalUser = WithdrawalUser(),
) {
    suspend fun withdrawal(call: ApplicationCall) {
        val req = call.receive<WithdrawalUser.Req>()
        withdrawalUser(req)
        call.respond(HttpStatusCode.OK)
    }
}