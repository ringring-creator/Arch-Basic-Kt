package com.ring.ring.router.user

import com.ring.ring.ui.user.loginView
import com.ring.ring.usecase.user.CreateUser
import com.ring.ring.usecase.user.EditUser
import com.ring.ring.usecase.user.Login
import com.ring.ring.usecase.user.Logout
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*

fun Route.userApiRouting() {
    route("/user/api") {
        post {
            val parameters = call.receiveParameters()
            val req = convertCreateUserReq(parameters)
            val createUser = CreateUser()
            createUser(req)
            call.respondRedirect("/user/login")
        }
        post("edit") {
            val parameters = call.receiveParameters()
            val req = convertEditUserReq(parameters)
            val editUser = EditUser()
            editUser(req)
            call.respondRedirect("/user/mypage")
        }
        post("delete") {
        }
        post("login") {
            val parameters = call.receiveParameters()
            val req = convertLoginReq(parameters)
            val login = Login()
            val res = login(req)
            res.session?.let { session ->
                call.sessions.set(session)
                call.respondRedirect("/todo/list")
            } ?: run {
                call.respondHtml(HttpStatusCode.Unauthorized) { loginView() }
            }
        }
        post("logout") {
            val session = call.sessions.get<Login.Res.Session>()
            session?.let {
                val logout = Logout()
                logout(Logout.Req(it.userId, it.credential))
            }
            call.respondRedirect("/user/login")
        }
    }
}

private fun convertEditUserReq(parameters: Parameters): EditUser.Req = EditUser.Req(
    id = parameters.getOrFail("id").toLong(),
    email = parameters.getOrFail("email"),
    password = parameters.getOrFail("password"),
)

private fun convertLoginReq(parameters: Parameters): Login.Req = Login.Req(
    email = parameters.getOrFail("email"),
    password = parameters.getOrFail("password"),
)

private fun convertCreateUserReq(parameters: Parameters): CreateUser.Req {
    val email = parameters.getOrFail("email")
    val password = parameters.getOrFail("password")
    return CreateUser.Req(
        email = email,
        password = password,
    )
}
