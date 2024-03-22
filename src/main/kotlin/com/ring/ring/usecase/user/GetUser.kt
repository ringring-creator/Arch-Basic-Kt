package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase

class GetUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = repository.get(req.id)
        return Res(user = user.toGetUser())
    }

    data class Req(
        val id: Long,
    ) : UseCase.Req

    data class Res(
        val user: User
    ) : UseCase.Res {
        data class User(
            val id: String,
            val email: String,
            val password: String,
        )
    }
}