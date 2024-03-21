package com.ring.ring.router.user

import com.ring.ring.ui.user.signUpView
import com.ring.ring.ui.user.loginView
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*


fun Route.userViewRouting() {
    route("/user") {
        get("signup") {
            call.respondHtml(HttpStatusCode.OK) { signUpView() }
        }
        get("delete") {

        }
        get("update") {

        }
        get("login") {
            call.respondHtml(HttpStatusCode.OK) { loginView() }
        }
        get("logout") {

        }
    }
}