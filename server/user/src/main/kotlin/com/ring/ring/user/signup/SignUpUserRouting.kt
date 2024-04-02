package com.ring.ring.user.signup

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.signUpUserRestApiRouting(
    controller: SignUpUserController = SignUpUserController()
) {
    route("/user") {
        post("signup") { controller.signUp(call) }
    }
}