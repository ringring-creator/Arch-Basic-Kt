package com.ring.ring.ui.user.withdrawal

import com.ring.ring.usecase.user.Withdrawal
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class WithdrawalViewModel(
    private val withdrawalUseCase: Withdrawal = Withdrawal()
) : WithdrawalUiUpdater {
    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()

    override suspend fun withdrawal() {
        withdrawalUseCase(Withdrawal.Req())
        _toLoginScreenEvent.trySend(Unit)
    }
}