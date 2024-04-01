package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.repository.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class CreateUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<CreateUser.Req, CreateUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.toUser()
        repository.save(user = user)
        return Res()
    }

    @Serializable
    data class Req(
        val email: String,
        val password: String,
    ) : UseCase.Req {
        fun toUser(): User = User(
            id = null,
            email = email,
            password = password,
        )
    }

    class Res : UseCase.Res
}