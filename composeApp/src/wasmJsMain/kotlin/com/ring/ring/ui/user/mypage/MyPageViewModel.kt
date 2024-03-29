package com.ring.ring.ui.user.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.user.GetUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyPageViewModel(
    private val getUserUseCase: GetUser = GetUser()
) {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    suspend fun getUser() {
        val res = getUserUseCase(GetUser.Req())
        _email.value = res.user.email
    }

    companion object {
        @Composable
        fun rememberLoginUiState(viewModel: MyPageViewModel): MyPageUiState {
            val email by viewModel.email.collectAsState()
            return MyPageUiState(
                email = email,
            )
        }
    }
}