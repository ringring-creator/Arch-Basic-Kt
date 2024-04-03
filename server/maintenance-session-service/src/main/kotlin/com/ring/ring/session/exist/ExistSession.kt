package com.ring.ring.session.exist

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.UseCase
import kotlinx.serialization.Serializable


internal class ExistSession(
    private val repository: ExistSessionRepository = ExistSessionRepository(),
) : UseCase<ExistSession.Req, ExistSession.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = repository.validate(req.toSession())
        return Res(isValid)
    }

    @Serializable
    data class Req(
        val userId: Long,
        val credential: String,
    ) : UseCase.Req {
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