package com.ring.ring.usecase.user

import com.ring.ring.data.UserRepository
import com.ring.ring.data.db.User
import com.ring.ring.usecase.UseCase

class CreateUser(
    private val repository: UserRepository = UserRepository(),
) : UseCase<CreateUser.Req, CreateUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.toUser()
        repository.save(user = user)
        return Res()
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