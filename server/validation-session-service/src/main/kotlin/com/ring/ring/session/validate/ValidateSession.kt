package com.ring.ring.session.validate

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.UseCase
import kotlinx.serialization.Serializable

internal class ValidateSession(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionModules.validateSessionRepository,
) : UseCase<ValidateSession.ReqSession, ValidateSession.Res>() {
    override suspend fun execute(req: ReqSession): Res {
        return Res(
            isValid = sessionRepository.validate(session = req.toSession())
        )
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
    data class Res(
        val isValid: Boolean
    ) : UseCase.Res
}