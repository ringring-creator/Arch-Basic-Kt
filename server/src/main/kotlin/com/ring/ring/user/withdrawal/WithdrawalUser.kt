package com.ring.ring.user.withdrawal

import com.ring.ring.di.DataModules
import com.ring.ring.session.validate.ValidateSession
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class WithdrawalUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: WithdrawalUserRepository = DataModules.withdrawalUserRepository,
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