package com.ring.ring.usecase.user

import com.ring.ring.data.Session
import com.ring.ring.data.SessionRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase

class Logout(
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Logout.Req, Logout.Res>() {
    override suspend fun execute(req: Req): Res {
        sessionRepository.delete(req.toSession())
        return Res()
    }

    data class Req(
        val userId: Long,
        val credential: String,
    ) : UseCase.Req {
        fun toSession(): Session = Session(
            userId = userId,
            credential = credential,
        )
    }

    class Res : UseCase.Res
}