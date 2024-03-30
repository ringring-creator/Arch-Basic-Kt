package com.ring.ring.ui.user.logout

import com.ring.ring.usecase.user.Logout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LogoutViewModel(
    private val logoutUseCase: Logout = Logout()
) : LogoutUiUpdater {
    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()

    override suspend fun logout() {
        logoutUseCase(Logout.Req())
        _toLoginScreenEvent.trySend(Unit)
    }
}