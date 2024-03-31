package com.ring.ring.ui.user.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.user.Login
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class LoginViewModel(
    private val loginUseCase: Login = Login()
) : LoginUiUpdater {
    private val uiState: UiState = UiState.init()

    private val _toTodoListScreenEvent = Channel<Unit>()
    val toTodoListScreenEvent = _toTodoListScreenEvent.receiveAsFlow()

    private val _loginEvent = Channel<Unit>()
    val loginEvent = _loginEvent.receiveAsFlow()

    override fun setEmail(email: String) {
        uiState.setEmail(email)
    }

    override fun setPassword(password: String) {
        uiState.setPassword(password)
    }

    override suspend fun login() {
        try {
            loginUseCase(uiState.toLoginRes())
            _toTodoListScreenEvent.trySend(Unit)
        } catch (e: Throwable) {
            _loginEvent.trySend(Unit)
        }
    }

    @Composable
    fun rememberLoginUiState(): LoginUiState {
        val email by uiState.email.collectAsState()
        val password by uiState.password.collectAsState()
        return LoginUiState(
            email = email,
            password = password,
        )
    }

    private data class UiState(
        val _email: MutableStateFlow<String>,
        val _password: MutableStateFlow<String>,
    ) {
        val email = _email.asStateFlow()
        val password = _password.asStateFlow()

        fun setEmail(email: String) {
            if (this.email.value == email) return
            _email.value = email
        }

        fun setPassword(password: String) {
            if (this.password.value == password) return
            _password.value = password
        }

        fun toLoginRes(): Login.Req = Login.Req(email.value, password.value)

        companion object {
            fun init(): UiState {
                return UiState(
                    _email = MutableStateFlow(""),
                    _password = MutableStateFlow(""),
                )
            }
        }
    }
}