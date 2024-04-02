package com.ring.ring.logout

import com.ring.ring.com.ring.ring.UseCase
import com.ring.ring.com.ring.ring.logout.LogoutModules
import com.ring.ring.validate.ValidateSession
import kotlinx.serialization.Serializable

class Logout(
    private val validateSession: ValidateSession = ValidateSession(),
    private val sessionRepository: DeleteSessionRepository = LogoutModules.deleteSessionRepository,
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