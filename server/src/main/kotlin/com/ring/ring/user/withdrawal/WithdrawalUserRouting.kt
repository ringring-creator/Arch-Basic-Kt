package com.ring.ring.user.withdrawal

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.withdrawalUserRouting(
    controller: WithdrawalUserController = WithdrawalUserController()
) {
    route("/user") {
        post("withdrawal") { controller.withdrawal(call) }
    }
}