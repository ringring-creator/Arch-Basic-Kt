package com.ring.ring.todo.shared

import com.ring.ring.data.db.Session
import kotlinx.serialization.Serializable

internal class ValidateSession(
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