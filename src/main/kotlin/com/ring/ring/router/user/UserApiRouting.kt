package com.ring.ring.router.user

import com.ring.ring.usecase.user.CreateUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

data class Session(
    val userId: String,
    val credential: String,
)

fun Route.apiUserRouting() {
    route("/user/api") {
        get("{id}") {

        }
        post {
            val parameters = call.receiveParameters()
            val req: CreateUser.Req = convertCreateUserReq(parameters)
            val createUser = CreateUser()
            createUser(req)
            call.respondRedirect("/user/login")
        }
        put {

        }
        delete("{id}") {

        }
        post("login") {
            call.respondHtml(HttpStatusCode.OK) {}
        }
        post("logout") {

        }
    }
}

private fun convertCreateUserReq(parameters: Parameters): CreateUser.Req {
    val email = parameters.getOrFail("email")
    val password = parameters.getOrFail("password")
    return CreateUser.Req(
        email = email,
        password = password,
    )
}
