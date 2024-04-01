package com.ring.ring.session.validate

import com.ring.ring.di.DataModules
import com.ring.ring.exception.NotLoggedInException
import com.ring.ring.session.Session
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class ValidateSession(
    private val sessionRepository: ValidateSessionRepository = DataModules.validateSessionRepository,
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