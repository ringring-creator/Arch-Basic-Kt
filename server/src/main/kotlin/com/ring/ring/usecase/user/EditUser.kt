package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class EditUser(
    private val userRepository: UserRepository = DataModules.userRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val user = req.user.toUser()
        userRepository.save(user = user)
        return Res()
    }

    @Serializable
    data class Req(
        val user: ReqUser,
        val session: Session,
    ) : UseCase.Req {
        @Serializable
        data class ReqUser(
            val id: Long,
            val email: String,
            val password: String,
        ) {
            fun toUser(): User = User(
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