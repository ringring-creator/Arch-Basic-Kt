package com.ring.ring.session.login

import com.ring.ring.session.UseCase
import kotlinx.serialization.Serializable

internal class Login(
    private val userRepository: ExistUserRepository = LoginModules.getUserRepository,
    private val sessionRepository: SaveSessionRepository = LoginModules.saveSessionRepository,
) : UseCase<Login.Req, Login.Res>() {
    override suspend fun execute(req: Req): Res {
        val userId = userRepository.exist(req.user.toUser())
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