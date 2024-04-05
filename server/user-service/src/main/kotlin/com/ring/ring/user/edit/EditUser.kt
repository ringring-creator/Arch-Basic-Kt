package com.ring.ring.user.edit

import com.ring.ring.user.shared.*
import kotlinx.serialization.Serializable

internal class EditUser(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val userRepository: EditUserRepository = EditUserModules.editUserRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        userRepository.save(
            user = req.user.toUser()
        )
        return Res()
    }

    private suspend fun validateSession(session: Session) {
        val isInvalid = sessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val user: ReqUser,
        val session: Session,
    ) : UseCase.Req {
        @Serializable
        data class ReqUser(
            val id: Long,
            val email: String,
            val password: String,
        ) {
            fun toUser(): User = User(
                id = id,
                email = email,
                password = password,
            )
        }
    }

    class Res : UseCase.Res
}