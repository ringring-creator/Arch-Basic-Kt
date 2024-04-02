package com.ring.ring.session.logout

import com.ring.ring.session.UseCase
import com.ring.ring.session.validate.ValidateSession
import kotlinx.serialization.Serializable

internal class Logout(
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