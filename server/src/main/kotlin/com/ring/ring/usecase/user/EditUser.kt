package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase

class EditUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.toUser()
        repository.save(user = user)
        return Res()
    }

    data class Req(
        val id: Long,
        val email: String,
        val password: String,
    ) : UseCase.Req {
        fun toUser(): User = User(
            id = id,
            email = email,
            password = password,
        )
    }

    class Res : UseCase.Res
}