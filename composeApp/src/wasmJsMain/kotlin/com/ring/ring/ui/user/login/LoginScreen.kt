package com.ring.ring.ui.user.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = remember { LoginViewModel() },
    toSignUpScreen: () -> Unit,
    toTodoListScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LoginScreen(
        uiState = LoginViewModel.rememberLoginUiState(viewModel),
        updater = viewModel,
        toSignUpScreen = toSignUpScreen,
        snackBarHostState = snackBarHostState,
    )

    setupSideEffect(viewModel, toTodoListScreen, snackBarHostState)
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
private fun setupSideEffect(
    viewModel: LoginViewModel,
    toTodoListScreen: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.toTodoListScreenEvent.collect {
            toTodoListScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loginEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to login",
                withDismissAction = true,
            )
        }
    }
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    updater: LoginUiUpdater,
    toSignUpScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }) { paddingValues ->
        Content(Modifier.padding(paddingValues), uiState, updater, scope, toSignUpScreen)
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: LoginUiState,
    updater: LoginUiUpdater,
    scope: CoroutineScope,
    toSignUpScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Header()
        EmailTextField(uiState, updater)
        PasswordTextField(uiState, updater, scope)
        Spacer(Modifier.height(8.dp))
        LoginButton(scope, updater)
        Spacer(Modifier.height(16.dp))
        SignUpText(toSignUpScreen)
    }
}

@Composable
private fun Header() {
    Text("Login", style = MaterialTheme.typography.headlineMedium)
}

@Composable
private fun EmailTextField(
    uiState: LoginUiState,
    updater: LoginUiUpdater
) {
    OutlinedTextField(
        value = uiState.email,
        onValueChange = updater::setEmail,
        label = { Text("Email address") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
    )
}

@Composable
private fun PasswordTextField(
    uiState: LoginUiState,
    updater: LoginUiUpdater,
    scope: CoroutineScope
) {
    OutlinedTextField(
        value = uiState.password,
        onValueChange = updater::setPassword,
        label = { Text("Password") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            scope.launch { updater.login() }
        })
    )
}

@Composable
private fun SignUpText(toSignUpScreen: () -> Unit) {
    Row {
        Text("Don't have an account?")
        ClickableText(
            annotatedString("Sign up"),
            modifier = Modifier,
            onClick = { toSignUpScreen() }
        )
    }
}

@Composable
private fun LoginButton(scope: CoroutineScope, updater: LoginUiUpdater) {
    Button(
        onClick = {
            scope.launch { updater.login() }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Login")
    }
}

@Composable
private fun annotatedString(text: String) = AnnotatedString.Builder(text).apply {
    addStyle(
        style = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        ).toSpanStyle(),
        start = 0,
        end = text.length
    )
}.toAnnotatedString()