package com.ring.ring.com.ring.ring.signup

import com.ring.ring.user.signup.SignUpUserController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.signUpUserRestApiRouting(
    controller: SignUpUserController = SignUpUserController()
) {
    route("/user") {
        post("signup") { controller.signUp(call) }
    }
}