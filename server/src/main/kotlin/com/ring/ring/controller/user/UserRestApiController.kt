package com.ring.ring.controller.user

import com.ring.ring.exception.BadRequestException
import com.ring.ring.usecase.user.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserRestApiController(
    private val createUser: CreateUser = CreateUser(),
    private val editUser: EditUser = EditUser(),
    private val withdrawalUser: WithdrawalUser = WithdrawalUser(),
    private val login: Login = Login(),
    private val logout: Logout = Logout(),
) {
    suspend fun signUp(call: ApplicationCall) {
        try {
            val req = call.receive<CreateUser.Req>()
            createUser(req)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
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
        val req = call.receive<Login.Req>()
        val res = login(req)
        call.respond(HttpStatusCode.OK, res)
    }

//    suspend fun logout(call: ApplicationCall) {
//        val session = call.sessions.get<Login.Res.Session>()
//        session?.let {
//            logout(Logout.Req(it.userId, it.credential))
//            call.sessions.clear<Login.Res.Session>()
//        }
//        call.respondRedirect("/user/login")
//    }

    private fun convertEditUserReq(parameters: Parameters): EditUser.Req = EditUser.Req(
        id = parameters["id"]?.toLong() ?: throw BadRequestException(message = "Id is not found."),
        email = parameters["email"] ?: throw BadRequestException(message = "Id is not found."),
        password = parameters["password"] ?: throw BadRequestException(message = "Id is not found."),
    )

    private fun convertWithdrawalUserReq(parameters: Parameters): WithdrawalUser.Req =
        WithdrawalUser.Req(
            id = parameters["id"]?.toLong()
                ?: throw BadRequestException(message = "Id is not found.")
        )

    private fun convertLoginReq(parameters: Parameters): Login.Req = Login.Req(
        email = parameters["email"] ?: throw BadRequestException(message = "Id is not found."),
        password = parameters["password"] ?: throw BadRequestException(message = "Id is not found."),
    )
}