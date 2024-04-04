package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.UseCase
import kotlinx.serialization.Serializable

internal class DeleteCacheById(
    private val sessionRepository: DeleteCacheRepository = DeleteCacheModules.validateSessionRepository,
) : UseCase<DeleteCacheById.ReqSession, DeleteCacheById.Res>() {
    override suspend fun execute(req: ReqSession): Res {
        sessionRepository.deleteById(req.userId)
        return Res()
    }

    @Serializable
    data class ReqSession(
        val userId: Long,
    ) : Req

    class Res : UseCase.Res
}