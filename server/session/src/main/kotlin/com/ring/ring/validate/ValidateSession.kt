package com.ring.ring.validate

import com.ring.ring.com.ring.ring.Session
import com.ring.ring.com.ring.ring.UseCase
import com.ring.ring.com.ring.ring.validate.NotLoggedInException
import com.ring.ring.com.ring.ring.validate.ValidateSessionModules
import kotlinx.serialization.Serializable

class ValidateSession(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionModules.validateSessionRepository,
) : UseCase<ValidateSession.ReqSession, ValidateSession.Res>() {
    override suspend fun execute(req: ReqSession): Res {
        val isInvalid = sessionRepository.validate(session = req.toSession()).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
        return Res()
    }

    @Serializable
    data class ReqSession(
        val userId: Long,
        val credential: String,
    ) : Req {
        fun toSession(): Session = Session(
            userId = userId,
            credential = credential,
        )
    }

    class Res : UseCase.Res
}