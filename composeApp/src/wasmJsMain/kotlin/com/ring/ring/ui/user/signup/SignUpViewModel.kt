package com.ring.ring.ui.user.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.user.SignUp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SignUpViewModel(
    private val signUpUseCase: SignUp = SignUp()
) : SignUpUiUpdater {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()
    private val _signUpErrorEvent = Channel<Unit>()
    val signUpErrorEvent = _signUpErrorEvent.receiveAsFlow()

    override fun setEmail(email: String) {
        if (this.email.value == email) return
        _email.value = email
    }

    override fun setPassword(password: String) {
        if (this.password.value == password) return
        _password.value = password
    }

    override suspend fun signUp() {
        try {
            signUpUseCase(SignUp.Req(email.value, password.value))
            _toLoginScreenEvent.trySend(Unit)
        } catch (e: Throwable) {
            _signUpErrorEvent.trySend(Unit)
        }
    }

    companion object {
        @Composable
        fun rememberSignUpUiState(viewModel: SignUpViewModel): SignUpUiState {
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            return SignUpUiState(
                email = email,
                password = password,
            )
        }
    }

}
