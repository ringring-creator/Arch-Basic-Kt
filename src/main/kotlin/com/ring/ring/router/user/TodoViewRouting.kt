package com.ring.ring.router.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*


fun Route.todoViewRouting() {
    route("/todo") {
        get("list") {
            call.respondHtml(HttpStatusCode.OK) { }
        }
        get("delete") {

        }
        get("update") {

        }
    }
}