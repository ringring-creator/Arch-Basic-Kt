package com.ring.ring.user.withdrawal

import com.ring.ring.user.UseCase
import com.ring.ring.user.shared.ValidateSession
import kotlinx.serialization.Serializable

internal class WithdrawalUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: WithdrawalUserRepository = WithdrawalUserModules.withdrawalUserRepository,
) : UseCase<WithdrawalUser.Req, WithdrawalUser.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.delete(id = req.session.userId)
        return Res()
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    class Res : UseCase.Res
}