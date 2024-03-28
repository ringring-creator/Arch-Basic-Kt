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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = remember { LoginViewModel() },
    toSignUpScreen: () -> Unit,
) {
    LoginScreen(
        uiState = LoginViewModel.rememberLoginUiState(viewModel),
        updater = viewModel,
        toSignUpScreen = toSignUpScreen
    )
}

data class LoginUiState(
    val email: String,
    val password: String,
)

interface LoginUiUpdater {
    fun setEmail(email: String)
    fun setPassword(password: String)
    fun login()
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    updater: LoginUiUpdater,
    toSignUpScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        // Email input field
        OutlinedTextField(
            value = uiState.email,
            onValueChange = updater::setEmail,
            label = { Text("Email address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(8.dp))

        // Password input field
        OutlinedTextField(
            value = uiState.password,
            onValueChange = updater::setPassword,
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                // Implement action on keyboard done, e.g., login
            })
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = updater::login,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(Modifier.height(24.dp))

        // Signup link
        Text("Don't have an account? Sign up", Modifier.clickable(onClick = toSignUpScreen))
    }
}