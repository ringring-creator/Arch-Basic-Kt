package com.ring.ring.usecase.user

import com.ring.ring.data.user.User
import com.ring.ring.data.user.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Login(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<Login.Req, Login.Res>() {
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

            email = email,
            password = password,
        )
    }

    class Res : UseCase.Res
}