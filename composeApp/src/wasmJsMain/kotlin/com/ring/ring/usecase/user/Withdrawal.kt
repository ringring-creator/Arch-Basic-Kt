package com.ring.ring.usecase.user

import com.ring.ring.data.SessionRepository
import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Withdrawal(
    private val userRepository: UserRepository = DataModules.userRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Withdrawal.Req, Withdrawal.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get()
        session?.let {
            userRepository.withdrawal(session.userId)
        }
        return@withContext Res()
    }

    class Req : UseCase.Req

    class Res : UseCase.Res
}