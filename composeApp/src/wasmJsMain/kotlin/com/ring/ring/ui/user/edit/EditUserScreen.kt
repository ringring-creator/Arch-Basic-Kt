package com.ring.ring.ui.user.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
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
import kotlin.reflect.KFunction1

@Composable
fun EditUserScreen(
    viewModel: EditUserViewModel = remember { EditUserViewModel() },
    toMyPageScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    EditUserScreen(
        uiState = viewModel.rememberEditUserUiState(),
        updater = viewModel,
        snackBarHostState = snackBarHostState,
        toMyPageScreen = toMyPageScreen,
    )

    SetupSideEffect(viewModel, toMyPageScreen, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: EditUserViewModel,
    toMyPageScreen: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.getUser()
    }
    LaunchedEffect(Unit) {
        viewModel.toMyPageScreenEvent.collect {
            toMyPageScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getUserErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to get user",
                withDismissAction = true,
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.editErrorEvent.collect {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = "Failed to edit",
                actionLabel = "Retry",
                withDismissAction = true,
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.getUser()
                }
            }
        }
    }
}

data class EditUserUiState(
    val email: String,
    val password: String,
    val editEnabled: Boolean,
)

interface EditUserUiUpdater {
    fun setEmail(email: String)
    fun setPassword(password: String)
    suspend fun edit()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(
    uiState: EditUserUiState,
    updater: EditUserUiUpdater,
    snackBarHostState: SnackbarHostState,
    toMyPageScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit User") },
                navigationIcon = {
                    IconButton(onClick = toMyPageScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            updater = updater,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: EditUserUiState,
    updater: EditUserUiUpdater,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        EmailTextField(uiState.email, updater::setEmail)
        PasswordTextField(uiState.password, updater::setPassword)
        EditButton(uiState.editEnabled) {
            scope.launch { updater.edit() }
        }
    }
}

@Composable
private fun EmailTextField(email: String, setEmail: KFunction1<String, Unit>) {
    OutlinedTextField(
        value = email,
        onValueChange = setEmail,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PasswordTextField(password: String, setPassword: KFunction1<String, Unit>) {
    OutlinedTextField(
        value = password,
        onValueChange = setPassword,
        label = { Text("New Password") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun EditButton(
    enabled: Boolean,
    edit: () -> Unit
) {
    Button(
        onClick = edit,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        enabled = enabled
    ) {
        Text("Edit")
    }
}