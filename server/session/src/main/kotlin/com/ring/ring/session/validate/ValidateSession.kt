package com.ring.ring.session.validate

import com.ring.ring.session.Session
import com.ring.ring.session.UseCase
import kotlinx.serialization.Serializable

class ValidateSession(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionModules.validateSessionRepository,
) : UseCase<ValidateSession.ReqSession, ValidateSession.Res>() {
    override suspend fun execute(req: ReqSession): Res {
        val isValid = sessionRepository.validate(session = req.toSession())
        return Res(isValid)
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

    @Serializable
    data class Res(val isValid: Boolean) : UseCase.Res
}