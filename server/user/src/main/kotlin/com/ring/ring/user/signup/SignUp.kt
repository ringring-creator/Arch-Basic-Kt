package com.ring.ring.user.signup

import com.ring.ring.data.db.User
import com.ring.ring.user.shared.UseCase
import kotlinx.serialization.Serializable

internal class SignUp(
    private val repository: SignUpUserRepository = SignUpUserModules.signUpUserRepository,
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