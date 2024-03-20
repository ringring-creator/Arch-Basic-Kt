package com.ring.ring.router.user

import com.ring.ring.ui.user.loginView
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*


fun Route.userViewRouting() {
    route("/user") {
        get("create") {

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