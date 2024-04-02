package com.ring.ring.user.get

import com.ring.ring.user.UseCase
import com.ring.ring.user.User
import com.ring.ring.user.shared.ValidateSession
import kotlinx.serialization.Serializable

class GetUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: GetUserRepository = GetUserModules.getUserRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val user = repository.get(req.session.userId)
        return Res(user = user.toReqUser())
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    @Serializable
    data class Res(
        val user: ReqUser
    ) : UseCase.Res {
        @Serializable
        data class ReqUser(
            val id: Long,
            val email: String,
            val password: String,
        )
    }

    private fun User.toReqUser(): Res.ReqUser {
        return Res.ReqUser(
            id = id ?: throw IllegalStateException(),
            email = email,
            password = password
        )
    }
}