package com.ring.ring.user.signup

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.signUpUserRestApiRouting() {
    val controller = SignUpUserController()
    route("/user") {
        post("signup") { controller.signUp(call) }
    }
}