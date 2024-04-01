package com.ring.ring.usecase.user

import com.ring.ring.data.repository.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import com.ring.ring.usecase.session.ValidateSession
import kotlinx.serialization.Serializable

class WithdrawalUser(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: UserRepository = DataModules.userRepository,
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