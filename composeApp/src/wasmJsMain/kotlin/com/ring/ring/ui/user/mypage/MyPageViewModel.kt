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
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _logoutEnabled = MutableStateFlow(false)
    val logoutEnabled = _logoutEnabled.asStateFlow()
    private val _editEnabled = MutableStateFlow(false)
    val editEnabled = _editEnabled.asStateFlow()
    private val _withdrawalEnabled = MutableStateFlow(false)
    val withdrawalEnabled = _withdrawalEnabled.asStateFlow()
    private val _getUserErrorEvent = Channel<Unit>()
    val getUserErrorEvent = _getUserErrorEvent.receiveAsFlow()

    suspend fun getUser() {
        try {
            val res = getUserUseCase(GetUser.Req())
            _email.value = res.user.email
            _logoutEnabled.value = true
            _editEnabled.value = true
            _withdrawalEnabled.value = true
        } catch (e: Throwable) {
            _getUserErrorEvent.trySend(Unit)
        }
    }

    companion object {
        @Composable
        fun rememberLoginUiState(viewModel: MyPageViewModel): MyPageUiState {
            val email by viewModel.email.collectAsState()
            val logoutEnabled by viewModel.logoutEnabled.collectAsState()
            val editEnabled by viewModel.editEnabled.collectAsState()
            val withdrawalEnabled by viewModel.withdrawalEnabled.collectAsState()
            return MyPageUiState(
                email = email,
                logoutEnabled = logoutEnabled,
                editEnabled = editEnabled,
                withdrawalEnabled = withdrawalEnabled,
            )
        }
    }
}