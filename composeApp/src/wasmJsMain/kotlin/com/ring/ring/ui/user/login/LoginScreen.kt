package com.ring.ring.ui.user.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = remember { LoginViewModel() },
    toSignUpScreen: () -> Unit,
    toTodoListScreen: () -> Unit,
) {
    LoginScreen(
        uiState = LoginViewModel.rememberLoginUiState(viewModel),
        updater = viewModel,
        toSignUpScreen = toSignUpScreen
    )

    LaunchedEffect(Unit) {
        viewModel.toTodoListScreenEvent.collect {
            toTodoListScreen()
        }
    }
}

data class LoginUiState(
    val email: String,
    val password: String,
)

interface LoginUiUpdater {
    fun setEmail(email: String)
    fun setPassword(password: String)
    suspend fun login()
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    updater: LoginUiUpdater,
    toSignUpScreen: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = uiState.email,
            onValueChange = updater::setEmail,
            label = { Text("Email address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = updater::setPassword,
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
            })
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch { updater.login() }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(Modifier.height(24.dp))

        Text("Don't have an account? Sign up", Modifier.clickable(onClick = toSignUpScreen))
    }
}