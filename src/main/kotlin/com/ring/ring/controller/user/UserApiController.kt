package com.ring.ring.controller.user

import com.ring.ring.ui.user.loginView
import com.ring.ring.usecase.user.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*

class UserApiController(
    private val createUser: CreateUser = CreateUser(),
    private val editUser: EditUser = EditUser(),
    private val withdrawalUser: WithdrawalUser = WithdrawalUser(),
    private val login: Login = Login(),
    private val logout: Logout = Logout(),
) {
    suspend fun create(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        val req = convertCreateUserReq(parameters)
        createUser(req)
        call.respondRedirect("/user/login")
    }

    suspend fun edit(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        val req = convertEditUserReq(parameters)
        editUser(req)
        call.respondRedirect("/user/mypage")
    }

    suspend fun delete(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        val req = convertWithdrawalUserReq(parameters)
        withdrawalUser(req)
        call.respondRedirect("/user/login")
    }

    suspend fun login(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        val req = convertLoginReq(parameters)
        val res = login(req)
        res.session?.let { session ->
            call.sessions.set(session)
            call.respondRedirect("/todo/list")
        } ?: run {
            call.respondHtml(HttpStatusCode.Unauthorized) { loginView() }
        }
    }

    suspend fun logout(call: ApplicationCall) {
        val session = call.sessions.get<Login.Res.Session>()
        session?.let {
            logout(Logout.Req(it.userId, it.credential))
        }
        call.respondRedirect("/user/login")
    }

    private fun convertCreateUserReq(parameters: Parameters): CreateUser.Req {
        val email = parameters.getOrFail("email")
        val password = parameters.getOrFail("password")
        return CreateUser.Req(
            email = email,
            password = password,
        )
    }

    private fun convertEditUserReq(parameters: Parameters): EditUser.Req = EditUser.Req(
        id = parameters.getOrFail("id").toLong(),
        email = parameters.getOrFail("email"),
        password = parameters.getOrFail("password"),
    )

    private fun convertWithdrawalUserReq(parameters: Parameters): WithdrawalUser.Req =
        WithdrawalUser.Req(id = parameters.getOrFail("id").toLong())

    private fun convertLoginReq(parameters: Parameters): Login.Req = Login.Req(
        email = parameters.getOrFail("email"),
        password = parameters.getOrFail("password"),
    )
}