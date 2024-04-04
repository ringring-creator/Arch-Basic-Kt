package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.Session
import com.ring.ring.validateSession.shared.UseCase
import kotlinx.serialization.Serializable

internal class DeleteCache(
    private val sessionRepository: DeleteCacheRepository = DeleteCacheModules.validateSessionRepository,
) : UseCase<DeleteCache.ReqSession, DeleteCache.Res>() {
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