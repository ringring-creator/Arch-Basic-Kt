package com.ring.ring.router.user

import com.ring.ring.controller.user.UserController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userViewRouting(
    controller: UserController = UserController()
) {
    route("") {
        get {
            call.respondRedirect("/user/login")
        }
    }
    route("/user") {
        get("signup") { controller.signup(call) }
        get("mypage") { controller.mypage(call) }
        get("withdrawal") { controller.withdrawal(call) }
        get("edit") { controller.edit(call) }
        get("login") { controller.login(call) }
        get("logout") { controller.logout(call) }
    }
}