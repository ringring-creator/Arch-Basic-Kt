package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import com.ring.ring.usecase.session.ValidateSession
import kotlinx.serialization.Serializable

class GetUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val user = repository.get(req.session.userId)
        return Res(user = user.toGetUser())
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    @Serializable
    data class Res(
        val user: User
    ) : UseCase.Res {
        @Serializable
        data class User(
            val id: Long,
            val email: String,
            val password: String,
        )
    }
}