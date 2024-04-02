package com.ring.ring.user.withdrawal

import com.ring.ring.user.UseCase
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import com.ring.ring.user.shared.ValidateSessionRepository
import kotlinx.serialization.Serializable

class WithdrawalUser(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val repository: WithdrawalUserRepository = WithdrawalUserModules.withdrawalUserRepository,
) : UseCase<WithdrawalUser.Req, WithdrawalUser.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
        repository.delete(id = req.session.userId)
        return Res()
    }

    @Serializable
    data class Req(
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}