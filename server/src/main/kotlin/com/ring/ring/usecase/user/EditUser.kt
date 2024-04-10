package com.ring.ring.usecase.user

import com.ring.ring.di.DataModules
import com.ring.ring.repository.User
import com.ring.ring.repository.UserRepository
import com.ring.ring.usecase.UseCase
import com.ring.ring.usecase.session.ValidateSession
import kotlinx.serialization.Serializable

class EditUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val userRepository: UserRepository = DataModules.userRepository,
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