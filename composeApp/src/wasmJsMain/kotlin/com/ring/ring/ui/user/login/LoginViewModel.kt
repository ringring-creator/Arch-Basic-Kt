package com.ring.ring.ui.user.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : LoginUiUpdater {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    override fun setEmail(email: String) {
        if (this.email.value == email) return
        _email.value = email
    }

    override fun setPassword(password: String) {
        if (this.password.value == password) return
        _password.value = password
    }

    override fun login() {
        TODO("Not yet implemented")
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