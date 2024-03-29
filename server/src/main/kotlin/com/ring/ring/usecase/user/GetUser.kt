package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class GetUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = repository.get(req.userId)
        return Res(user = user.toGetUser())
    }

    @Serializable
    data class Req(
        val userId: Long,
        val credential: String,
    ) : UseCase.Req

    @Serializable
    data class Res(
        val user: User
    ) : UseCase.Res {
        @Serializable
        data class User(
            val id: Long,
            val email: String,
            val password: String,
        )
    }
}