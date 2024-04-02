package com.ring.ring.com.ring.ring.edit

import com.ring.ring.user.edit.EditUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class EditUserController(
    private val editUser: EditUser = EditUser(),
) {
    suspend fun edit(call: ApplicationCall) {
        val req = call.receive<EditUser.Req>()
        editUser(req)
        call.respond(HttpStatusCode.OK)
    }
}