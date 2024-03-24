package com.ring.ring.controller.user


import com.ring.ring.ui.user.*
import com.ring.ring.usecase.user.GetUser
import com.ring.ring.usecase.user.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.sessions.*


class UserController(
    private val getUser: GetUser = GetUser()
) {
    suspend fun signup(call: ApplicationCall) {
        call.respondHtml(HttpStatusCode.OK) { signUpView() }
    }

    suspend fun mypage(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        val res = getUser(GetUser.Req(session!!.userId))
        call.respondHtml(HttpStatusCode.OK) {
            mypageView(res)
        }
    }

    suspend fun withdrawal(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        val res = getUser(GetUser.Req(session!!.userId))
        call.respondHtml(HttpStatusCode.OK) {
            withdrawalUserView(res)
        }
    }

    suspend fun edit(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        val res = getUser(GetUser.Req(session!!.userId))
        call.respondHtml(HttpStatusCode.OK) {
            editUserView(res)
        }
    }

    suspend fun login(call: ApplicationCall) {
        call.respondHtml(HttpStatusCode.OK) { loginView() }
    }

    suspend fun logout(call: ApplicationCall) {
        call.respondHtml(HttpStatusCode.OK) { logoutView() }
    }
}