package com.ring.ring.session.logout

import com.ring.ring.session.shared.NotLoggedInException
import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.UseCase
import kotlinx.serialization.Serializable

internal class Logout(
    private val validateRepository: ValidateSessionRepository = LogoutModules.validateSessionRepository,
    private val deleteRepository: DeleteSessionRepository = LogoutModules.deleteSessionRepository,
) : UseCase<Logout.Req, Logout.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req)
        deleteRepository.delete(req.session)
        return Res()
    }

    private suspend fun validateSession(req: Req) {
        val isInvalid = validateRepository.validate(session = req.session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}