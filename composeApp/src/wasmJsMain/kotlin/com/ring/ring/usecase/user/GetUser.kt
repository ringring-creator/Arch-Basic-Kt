package com.ring.ring.usecase.user

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
import com.ring.ring.repository.SessionRepository
import com.ring.ring.repository.UserRepository
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUser(
    private val userRepository: UserRepository = DataModules.userRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<GetUser.Req, GetUser.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        val user = userRepository.get(session)
        return@withContext user.toGetUserElement()
    }

    class Req : UseCase.Req

    data class Res(
        val id: Long,
        val email: String,
        val password: String,
    ) : UseCase.Res

    private fun User.toGetUserElement(): Res {
        return Res(
            id = id!!,
            email = email,
            password = password,
        )
    }
}
