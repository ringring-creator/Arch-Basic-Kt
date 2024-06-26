package com.ring.ring.usecase.user

import com.ring.ring.data.Session
import com.ring.ring.data.User
import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Login(
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Login.Req, Login.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val user = req.toUser()
        val session = sessionRepository.login(user = user)
        sessionRepository.save(session)
        return@withContext session.toRes()
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

    data class Res(
        val userId: Long,
        val credential: String,
    ) : UseCase.Res

    private fun Session.toRes(): Res = Res(userId, credential)
}