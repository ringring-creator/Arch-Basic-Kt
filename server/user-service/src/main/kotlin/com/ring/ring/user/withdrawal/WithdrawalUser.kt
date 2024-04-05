package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.*
import kotlinx.serialization.Serializable

internal class WithdrawalUser(
    private val validateSessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val deleteTaskRepository: DeleteTaskRepository = SharedModules.deleteTaskRepository,
) : UseCase<WithdrawalUser.Req, WithdrawalUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        deleteTaskRepository.save(DeleteTask(DeleteTask.Type.Todo, req.session.userId))
        deleteTaskRepository.save(DeleteTask(DeleteTask.Type.Session, req.session.userId))
        deleteTaskRepository.save(DeleteTask(DeleteTask.Type.User, req.session.userId))
        return Res()
    }

    private suspend fun validateSession(session: Session) {
        val isInvalid = validateSessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}