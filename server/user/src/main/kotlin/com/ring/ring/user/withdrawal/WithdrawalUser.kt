package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.UseCase
import com.ring.ring.user.shared.ValidateSession
import kotlinx.serialization.Serializable

internal class WithdrawalUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val userRepository: WithdrawalUserRepository = WithdrawalUserModules.withdrawalUserRepository,
    private val todoRepository: DeleteTodoRepository = WithdrawalUserModules.todoRepository,
    private val sessionRepository: DeleteSessionRepository = WithdrawalUserModules.sessionRepository,
) : UseCase<WithdrawalUser.Req, WithdrawalUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        userRepository.delete(req.session.userId)
        todoRepository.delete(req.session.userId)
        sessionRepository.delete(req.session.userId)
        return Res()
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    class Res : UseCase.Res
}