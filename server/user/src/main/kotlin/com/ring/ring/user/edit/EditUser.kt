package com.ring.ring.user.edit

import com.ring.ring.user.UseCase
import com.ring.ring.user.User
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import com.ring.ring.user.shared.ValidateSessionRepository
import kotlinx.serialization.Serializable

class EditUser(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val userRepository: EditUserRepository = EditUserModules.editUserRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
        userRepository.save(
            user = req.user.toUser()
        )
        return Res()
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