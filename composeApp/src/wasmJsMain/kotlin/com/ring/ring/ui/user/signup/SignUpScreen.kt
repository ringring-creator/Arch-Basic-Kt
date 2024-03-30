package com.ring.ring.ui.user.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
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
    val snackBarHostState = remember { SnackbarHostState() }

    SignUpScreen(
        uiState = SignUpViewModel.rememberSignUpUiState(viewModel),
        updater = viewModel,
        toLoginScreen = toLoginScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, toLoginScreen, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: SignUpViewModel,
    toLoginScreen: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.toLoginScreenEvent.collect {
            toLoginScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.signUpErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to sign up",
                withDismissAction = true,
            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    updater: SignUpUiUpdater,
    toLoginScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sign Up") },
                navigationIcon = {
                    IconButton(onClick = toLoginScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            updater = updater,
            scope = scope
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: SignUpUiState,
    updater: SignUpUiUpdater,
    scope: CoroutineScope
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EmailTextField(uiState.email, updater::setEmail)
        PasswordTextField(uiState.password, updater::setPassword)
        Spacer(Modifier.height(8.dp))
        SignUpButton {
            scope.launch { updater.signUp() }
        }
    }
}

@Composable
private fun EmailTextField(
    email: String,
    setEmail: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = setEmail,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    setPassword: (String) -> Unit,
) {
    OutlinedTextField(
        value = password,
        onValueChange = setPassword,
        label = { Text("Password") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun SignUpButton(signUp: () -> Unit) {
    Button(
        onClick = signUp,
        modifier = Modifier.fillMaxWidth()
    ) { Text("Sign Up") }
}
