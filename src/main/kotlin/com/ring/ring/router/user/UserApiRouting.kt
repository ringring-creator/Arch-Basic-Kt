package com.ring.ring.router.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

data class User(
    val id: String,
    val email: String,
    val password: String,
)

data class Session(
    val userId: String,
    val credential: String,
)

fun Route.apiUserRouting() {
    route("/user/api/") {
        get("{id}") {

        }
        post {

        }
        put {

        }
        delete("{id}") {

        }
        post("login") {
            call.respondHtml(HttpStatusCode.OK){}
        }
        post("logout") {

        }
    }
}