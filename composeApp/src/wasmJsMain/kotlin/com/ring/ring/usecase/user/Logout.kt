package com.ring.ring.usecase.user

import com.ring.ring.data.session.SessionRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Logout(
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Logout.Req, Logout.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        sessionRepository.delete()
        return@withContext Res()
    }

    class Req : UseCase.Req

    class Res : UseCase.Res
}