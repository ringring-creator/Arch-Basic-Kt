package com.ring.ring.usecase.user

import com.ring.ring.data.session.SessionRepository
import com.ring.ring.data.user.User
import com.ring.ring.data.user.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUser(
    private val userRepository: UserRepository = DataModules.userRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.getSession() ?: throw Exception()
        val user = userRepository.get(session)
        return@withContext Res(user.toGetUserElement())
    }

    class Req : UseCase.Req

    data class Res(
        val user: User
    ) : UseCase.Res {
        data class User(
            val id: Long,
            val email: String,
            val password: String,
        )
    }
}