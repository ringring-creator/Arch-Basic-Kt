package com.ring.ring.ui.user.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditUserScreen(
    viewModel: EditUserViewModel = remember { EditUserViewModel() },
    toMyPageScreen: () -> Unit,
) {
    EditUserScreen(
        uiState = EditUserViewModel.rememberEditUserUiState(viewModel),
        updater = viewModel
    )

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }

    LaunchedEffect(Unit) {
        viewModel.toMyPageScreenEvent.collect {
            toMyPageScreen()
        }
    }
}

data class EditUserUiState(
    val email: String,
    val password: String,
)

interface EditUserUiUpdater {
    fun setEmail(email: String)
    fun setPassword(password: String)
    suspend fun edit()
}

@Composable
fun EditUserScreen(
    uiState: EditUserUiState,
    updater: EditUserUiUpdater,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit User") }) },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = updater::setEmail,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.password,
                onValueChange = updater::setPassword,
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    scope.launch { updater.edit() }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Edit")
            }

        }
    }
}