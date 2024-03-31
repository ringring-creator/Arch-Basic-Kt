package com.ring.ring.ui.user.withdrawal

import com.ring.ring.usecase.user.Withdrawal
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class WithdrawalViewModel(
    private val withdrawalUseCase: Withdrawal = Withdrawal()
) : WithdrawalUiUpdater {
    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()

    private val _withdrawalErrorEvent = Channel<Unit>()
    val withdrawalErrorEvent = _withdrawalErrorEvent.receiveAsFlow()

    override suspend fun withdrawal() {
        try {
            withdrawalUseCase(Withdrawal.Req())
        } catch (e: Throwable) {
            _withdrawalErrorEvent.trySend(Unit)
            return
        }
        _toLoginScreenEvent.trySend(Unit)
    }
}