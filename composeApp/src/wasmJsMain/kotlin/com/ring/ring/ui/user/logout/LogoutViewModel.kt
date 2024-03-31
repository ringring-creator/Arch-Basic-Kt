package com.ring.ring.ui.user.logout

import com.ring.ring.usecase.user.Logout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LogoutViewModel(
    private val logoutUseCase: Logout = Logout()
) : LogoutUiUpdater {
    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()

    private val _logoutErrorEvent = Channel<Unit>()
    val logoutErrorEvent = _logoutErrorEvent.receiveAsFlow()

    override suspend fun logout() {
        try {
            logoutUseCase(Logout.Req())
        } catch (e: Throwable) {
            _logoutErrorEvent.trySend(Unit)
            return
        }
        _toLoginScreenEvent.trySend(Unit)
    }
}