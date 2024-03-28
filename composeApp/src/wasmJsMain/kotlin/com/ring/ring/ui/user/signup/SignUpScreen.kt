package com.ring.ring.ui.user.signup

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
fun SignUpScreen(
    viewModel: SignUpViewModel = remember { SignUpViewModel() },
    toLoginScreen: () -> Unit,
) {
    SignUpScreen(
        uiState = SignUpViewModel.rememberSignUpUiState(viewModel),
        updater = viewModel
    )

    LaunchedEffect(Unit) {
        viewModel.toLoginScreenEvent.collect {
            toLoginScreen()
        }
    }
}

data class SignUpUiState(
    val email: String,
    val password: String,
)

interface SignUpUiUpdater {
    fun setEmail(email: String)
    fun setPassword(password: String)
    suspend fun signUp()
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    updater: SignUpUiUpdater,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

        // Email input field
        OutlinedTextField(
            value = uiState.email,
            onValueChange = updater::setEmail,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
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
                // Implement action on keyboard done, e.g., sign up
            })
        )

        Spacer(Modifier.height(16.dp))

        // Sign Up button
        Button(
            onClick = {
                scope.launch { updater.signUp() }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}
