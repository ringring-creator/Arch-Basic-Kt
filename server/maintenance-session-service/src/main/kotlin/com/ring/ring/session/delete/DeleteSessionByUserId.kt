package com.ring.ring.session.delete

import com.ring.ring.session.shared.UseCase
import kotlinx.serialization.Serializable

internal class DeleteSessionByUserId(
    private val sessionRepository: DeleteSessionByUserIdRepository = DeleteSessionModules.deleteSessionRepository,
) : UseCase<DeleteSessionByUserId.Req, DeleteSessionByUserId.Res>() {
    override suspend fun execute(req: Req): Res {
        sessionRepository.delete(req.userId)
        return Res()
    }

    @Serializable
    data class Req(
        val userId: Long,
    ) : UseCase.Req

    class Res : UseCase.Res
}