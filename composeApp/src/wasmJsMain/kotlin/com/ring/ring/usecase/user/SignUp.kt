package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
import com.ring.ring.repository.UserRepository
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUp(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<SignUp.Req, SignUp.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val user = req.toUser()
        repository.signUp(user = user)
        return@withContext Res()
    }

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