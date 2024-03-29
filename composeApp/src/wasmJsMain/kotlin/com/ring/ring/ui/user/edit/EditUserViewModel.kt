package com.ring.ring.ui.user.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.user.EditUser
import com.ring.ring.usecase.user.GetUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class EditUserViewModel(
    private val getUserUseCase: GetUser = GetUser(),
    private val editUserUseCase: EditUser = EditUser(),
) : EditUserUiUpdater {
    private var id: Long? = null
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _toMyPageScreenEvent = Channel<Unit>()
    val toMyPageScreenEvent = _toMyPageScreenEvent.receiveAsFlow()

    suspend fun getUser() {
        val res = getUserUseCase(GetUser.Req())
        id = res.user.id
        _email.value = res.user.email
        _password.value = res.user.password
    }

    override fun setEmail(email: String) {
        if (this.email.value == email) return
        _email.value = email
    }

    override fun setPassword(password: String) {
        if (this.password.value == password) return
        _password.value = password
    }

    override suspend fun edit() {
        id?.let {
            editUserUseCase(EditUser.Req(it, email.value, password.value))
        }
        _toMyPageScreenEvent.trySend(Unit)
    }

    companion object {
        @Composable
        fun rememberEditUserUiState(viewModel: EditUserViewModel): EditUserUiState {
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            return EditUserUiState(
                email = email,
                password = password,
            )
        }
    }
}