package com.ring.ring.user.get

import com.ring.ring.user.shared.*
import kotlinx.serialization.Serializable

internal class GetUser(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val repository: GetUserRepository = GetUserModules.getUserRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val user = repository.get(req.session.userId)
        return Res(user = user.toReqUser())
    }

    private suspend fun validateSession(session: Session) {
        val isInvalid = sessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val session: Session,
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