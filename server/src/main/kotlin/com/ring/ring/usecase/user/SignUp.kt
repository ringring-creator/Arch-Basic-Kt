package com.ring.ring.usecase.user

import com.ring.ring.di.DataModules
import com.ring.ring.repository.User
import com.ring.ring.repository.UserRepository
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class SignUp(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<SignUp.Req, SignUp.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.user.toUser()
        repository.save(user = user)
        return Res()
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

    class Res : UseCase.Res
}