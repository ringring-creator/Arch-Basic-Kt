package com.ring.ring.usecase.user

import com.ring.ring.data.SessionRepository
import com.ring.ring.data.User
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditUser(
    private val userRepository: UserRepository = DataModules.userRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<EditUser.Req, EditUser.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        userRepository.edit(req.toUser(), session)
        return@withContext Res()
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