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
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _toTodoListScreenEvent = Channel<Login.Res.Session>()
    val toTodoListScreenEvent = _toTodoListScreenEvent.receiveAsFlow()

    override fun setEmail(email: String) {
        if (this.email.value == email) return
        _email.value = email
    }

    override fun setPassword(password: String) {
        if (this.password.value == password) return
        _password.value = password
    }

    override suspend fun login() {
        val res = loginUseCase(
            Login.Req(email.value, password.value)
        )
        _toTodoListScreenEvent.trySend(res.session)
    }

    companion object {
        @Composable
        fun rememberLoginUiState(viewModel: LoginViewModel): LoginUiState {
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            return LoginUiState(
                email = email,
                password = password,
            )
        }
    }
}