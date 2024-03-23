package com.ring.ring.usecase.user

import com.ring.ring.data.UserRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase

class WithdrawalUser(
    private val repository: UserRepository = DataModules.userRepository,
) : UseCase<WithdrawalUser.Req, WithdrawalUser.Res>() {
    override suspend fun execute(req: Req): Res {
        repository.delete(id = req.id)
        return Res()
    }

    data class Req(val id: Long) : UseCase.Req

    class Res : UseCase.Res
}