package com.ring.ring.router.user

import com.ring.ring.ui.user.loginView
import com.ring.ring.ui.user.signUpView
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userViewRouting() {
    route("") {
        get {
            call.respondRedirect("/user/login")
        }
    }
    route("/user") {
        get("signup") {
            call.respondHtml(HttpStatusCode.OK) { signUpView() }
        }
        get("mypage") {
            call.respondHtml(HttpStatusCode.OK) { }
        }
        get("delete") {

        }
        get("edit") {

        }
        get("login") {
            call.respondHtml(HttpStatusCode.OK) { loginView() }
        }
        get("logout") {

        }
    }
}