package com.ring.ring.usecase.session

import com.ring.ring.di.DataModules
import com.ring.ring.repository.SessionRepository
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class Logout(
    private val validateSession: ValidateSession = ValidateSession(),
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<Logout.Req, Logout.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        sessionRepository.delete(req.session.toSession())
        return Res()
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    class Res : UseCase.Res
}