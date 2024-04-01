package com.ring.ring.session.logout

import com.ring.ring.di.DataModules
import com.ring.ring.session.validate.ValidateSession
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class Logout(
    private val validateSession: ValidateSession = ValidateSession(),
    private val sessionRepository: DeleteSessionRepository = DataModules.deleteSessionRepository,
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