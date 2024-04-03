package com.ring.ring.session.delete

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.UseCase
import kotlinx.serialization.Serializable

internal class DeleteSession(
    private val sessionRepository: DeleteSessionRepository = DeleteSessionModules.validateSessionRepository,
) : UseCase<DeleteSession.ReqSession, DeleteSession.Res>() {
    override suspend fun execute(req: ReqSession): Res {
        sessionRepository.delete(req.toSession())
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