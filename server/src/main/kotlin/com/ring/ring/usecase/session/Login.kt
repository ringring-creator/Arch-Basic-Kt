package com.ring.ring.usecase.session

import com.ring.ring.data.User
import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.data.repository.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.exception.LoginFailureException
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class Login(
    private val userRepository: UserRepository = DataModules.userRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Login.Req, Login.Res>() {
    override suspend fun execute(req: Req): Res {
        val userId = userRepository.loadId(req.user.toUser())
            ?: throw LoginFailureException(message = "Id is not found.")
        val session = sessionRepository.save(userId)
        return Res(session.userId, session.credential)
    }

    @Serializable
    data class Req(
        val user: ReqUser,
    ) : UseCase.Req {
        @Serializable
        data class ReqUser(
            val email: String,
            val password: String,
        ) {
            fun toUser(): User = User(
                id = null,
                email = email,
                password = password,
            )
        }
    }

    @Serializable
    data class Res(
        val userId: Long,
        val credential: String,
    ) : UseCase.Res
}