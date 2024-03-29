package com.ring.ring.usecase.user

import com.ring.ring.data.Session
import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class EditUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.user.toUser()
        repository.save(user = user)
        return Res()
    }

    @Serializable
    data class Req(
        val user: User,
        val session: Session,
    ) : UseCase.Req {
        @Serializable
        data class User(
            val id: Long,
            val email: String,
            val password: String,
        ) {
            fun toUser(): com.ring.ring.data.User = com.ring.ring.data.User(
                id = id,
                email = email,
                password = password,
            )
        }

        @Serializable
        data class Session(
            val userId: Long,
            val credential: String,
        )
    }

    class Res : UseCase.Res
}