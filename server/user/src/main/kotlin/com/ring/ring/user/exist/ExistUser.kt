package com.ring.ring.user.exist

import com.ring.ring.user.UseCase
import com.ring.ring.user.User
import kotlinx.serialization.Serializable

internal class ExistUser(
    private val repository: ExistUserRepository = ExistUserModules.getUserRepository,
) : UseCase<ExistUser.Req, ExistUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val userId = repository.exist(req.user.toUser())
        return Res(userId = userId)
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
        val userId: Long?
    ) : UseCase.Res
}