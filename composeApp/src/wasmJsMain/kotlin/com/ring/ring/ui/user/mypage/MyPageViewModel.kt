package com.ring.ring.ui.user.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.user.GetUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class MyPageViewModel(
    private val getUserUseCase: GetUser = GetUser()
) {
    private val uiState: UiState = UiState.init()

    private val _getUserErrorEvent = Channel<Unit>()
    val getUserErrorEvent = _getUserErrorEvent.receiveAsFlow()

    suspend fun getUser() {
        try {
            val res = getUserUseCase(GetUser.Req())
            uiState.set(res.user)
        } catch (e: Throwable) {
            _getUserErrorEvent.trySend(Unit)
        }
    }

    @Composable
    fun rememberLoginUiState(): MyPageUiState {
        val email by uiState.email.collectAsState()
        val logoutEnabled by uiState.logoutEnabled.collectAsState()
        val editEnabled by uiState.editEnabled.collectAsState()
        val withdrawalEnabled by uiState.withdrawalEnabled.collectAsState()
        return MyPageUiState(
            email = email,
            logoutEnabled = logoutEnabled,
            editEnabled = editEnabled,
            withdrawalEnabled = withdrawalEnabled,
        )
    }

    private data class UiState(
        val _email: MutableStateFlow<String>,
        val _logoutEnabled: MutableStateFlow<Boolean>,
        val _editEnabled: MutableStateFlow<Boolean>,
        val _withdrawalEnabled: MutableStateFlow<Boolean>,
    ) {
        fun set(user: GetUser.Res.User) {
            _email.value = user.email
            _logoutEnabled.value = true
            _editEnabled.value = true
            _withdrawalEnabled.value = true
        }

        val email = _email.asStateFlow()
        val logoutEnabled = _logoutEnabled.asStateFlow()
        val editEnabled = _editEnabled.asStateFlow()
        val withdrawalEnabled = _withdrawalEnabled.asStateFlow()

        companion object {
            fun init(): UiState {
                return UiState(
                    _email = MutableStateFlow(""),
                    _logoutEnabled = MutableStateFlow(false),
                    _editEnabled = MutableStateFlow(false),
                    _withdrawalEnabled = MutableStateFlow(false),
                )
            }
        }
    }
}