package com.ring.ring.router

import com.ring.ring.controller.UserRestApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.userRestApiRouting(
    controller: UserRestApiController = UserRestApiController()
) {
    route("/user") {
        post("get") { controller.get(call) }
        post("signup") { controller.signUp(call) }
        post("edit") { controller.edit(call) }
        post("withdrawal") { controller.withdrawal(call) }
    }
}