package com.ring.ring.router.user

import com.ring.ring.ui.user.*
import com.ring.ring.usecase.user.GetUser
import com.ring.ring.usecase.user.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


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
            val session = call.sessions.get<Login.Res.Session>()
            val getUser = GetUser()
            val res = getUser(GetUser.Req(session!!.userId))
            call.respondHtml(HttpStatusCode.OK) {
                mypageView(res)
            }
        }
        get("withdrawal") {
            val session = call.sessions.get<Login.Res.Session>()
            val getUser = GetUser()
            val res = getUser(GetUser.Req(session!!.userId))
            call.respondHtml(HttpStatusCode.OK) {
                withdrawalUserView(res)
            }
        }
        get("edit") {
            val session = call.sessions.get<Login.Res.Session>()
            val getUser = GetUser()
            val res = getUser(GetUser.Req(session!!.userId))
            call.respondHtml(HttpStatusCode.OK) {
                editUserView(res)
            }
        }
        get("login") {
            call.respondHtml(HttpStatusCode.OK) { loginView() }
        }
        get("logout") {
            call.respondHtml(HttpStatusCode.OK) { logoutView() }
        }
    }
}