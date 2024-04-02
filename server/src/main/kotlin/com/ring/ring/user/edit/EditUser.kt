package com.ring.ring.user.edit

import com.ring.ring.user.UseCase
import com.ring.ring.user.User
import com.ring.ring.validate.ValidateSession
import kotlinx.serialization.Serializable

class EditUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val userRepository: EditUserRepository = EditUserModules.editUserRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        userRepository.save(
            user = req.user.toUser()
        )
        return Res()
    }

    @Serializable
    data class Req(
        val user: ReqUser,
        val session: ValidateSession.ReqSession,
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