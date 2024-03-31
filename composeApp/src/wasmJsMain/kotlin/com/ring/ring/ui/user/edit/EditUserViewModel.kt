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
    private val uiState = UiState.init()

    private val _toMyPageScreenEvent = Channel<Unit>()
    val toMyPageScreenEvent = _toMyPageScreenEvent.receiveAsFlow()

    private val _getUserErrorEvent = Channel<Unit>()
    val getUserErrorEvent = _getUserErrorEvent.receiveAsFlow()
    private val _editErrorEvent = Channel<Unit>()
    val editErrorEvent = _editErrorEvent.receiveAsFlow()

    suspend fun getUser() {
        val res = try {
            getUserUseCase(GetUser.Req())
        } catch (e: Throwable) {
            _getUserErrorEvent.trySend(Unit)
            return
        }
        uiState.set(res)
    }

    override suspend fun edit() {
        try {
            editUserUseCase(uiState.toEditUserReq())
        } catch (e: Throwable) {
            _editErrorEvent.trySend(Unit)
            return
        }
        _toMyPageScreenEvent.trySend(Unit)
    }

    override fun setEmail(email: String) {
        uiState.setEmail(email)
    }

    override fun setPassword(password: String) {
        uiState.setPassword(password)
    }

    @Composable
    fun rememberEditUserUiState(): EditUserUiState {
        val email by uiState.email.collectAsState()
        val password by uiState.password.collectAsState()
        val editEnabled by uiState.editEnabled.collectAsState()
        return EditUserUiState(
            email = email,
            password = password,
            editEnabled = editEnabled,
        )
    }

    private data class UiState(
        var id: Long?,
        val _email: MutableStateFlow<String>,
        val _password: MutableStateFlow<String>,
        val _editEnabled: MutableStateFlow<Boolean>,
    ) {
        val email = _email.asStateFlow()
        val password = _password.asStateFlow()
        val editEnabled = _editEnabled.asStateFlow()

        fun set(user: GetUser.Res) {
            id = user.id
            _email.value = user.email
            _password.value = user.password
            _editEnabled.value = true
        }

        fun unwrapId() = id ?: run {
            throw IllegalStateException()
        }

        fun toEditUserReq(): EditUser.Req {
            return EditUser.Req(unwrapId(), email.value, password.value)
        }

        fun setEmail(email: String) {
            if (this.email.value == email) return
            _email.value = email
        }

        fun setPassword(password: String) {
            if (this.password.value == password) return
            _password.value = password
        }

        companion object {
            fun init(): UiState = UiState(
                id = null,
                _email = MutableStateFlow(""),
                _password = MutableStateFlow(""),
                _editEnabled = MutableStateFlow(false),
            )
        }
    }
}