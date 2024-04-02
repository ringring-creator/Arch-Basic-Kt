package com.ring.ring.session.login

import com.ring.ring.user.UseCase
import com.ring.ring.user.User
import com.ring.ring.user.get.GetUserRepository
import kotlinx.serialization.Serializable

class Login(
    private val userRepository: GetUserRepository = LoginModules.getUserRepository,
    private val sessionRepository: SaveSessionRepository = LoginModules.saveSessionRepository,
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