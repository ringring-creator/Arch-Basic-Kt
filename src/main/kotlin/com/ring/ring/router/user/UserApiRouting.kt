package com.ring.ring.router.user

import com.ring.ring.controller.user.UserApiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.userApiRouting(
    controller: UserApiController = UserApiController()
) {
    route("/user/api") {
        post { controller.create(call) }
        post("edit") { controller.edit(call) }
        post("delete") { controller.delete(call) }
        post("login") { controller.login(call) }
        post("logout") { controller.logout(call) }
    }
}