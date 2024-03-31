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
    private val uiState: UiState = UiState.init()

    private val _toLoginScreenEvent = Channel<Unit>()
    val toLoginScreenEvent = _toLoginScreenEvent.receiveAsFlow()

    private val _signUpErrorEvent = Channel<Unit>()
    val signUpErrorEvent = _signUpErrorEvent.receiveAsFlow()

    override fun setEmail(email: String) {
        uiState.setEmail(email)
    }

    override fun setPassword(password: String) {
        uiState.setPassword(password)
    }

    override suspend fun signUp() {
        try {
            signUpUseCase(uiState.toSignUpReq())
            _toLoginScreenEvent.trySend(Unit)
        } catch (e: Throwable) {
            _signUpErrorEvent.trySend(Unit)
        }
    }

    @Composable
    fun rememberSignUpUiState(): SignUpUiState {
        val email by uiState.email.collectAsState()
        val password by uiState.password.collectAsState()
        return SignUpUiState(
            email = email,
            password = password,
        )
    }

    private data class UiState(
        val _email: MutableStateFlow<String> = MutableStateFlow(""),
        val _password: MutableStateFlow<String> = MutableStateFlow(""),
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

        fun toSignUpReq(): SignUp.Req = SignUp.Req(email.value, password.value)

        companion object {
            fun init(): UiState = UiState(
                _email = MutableStateFlow(""),
                _password = MutableStateFlow(""),
            )
        }
    }
}
