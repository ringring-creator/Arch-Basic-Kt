package com.ring.ring.ui.user.logout


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LogoutScreen(
    viewModel: LogoutViewModel = remember { LogoutViewModel() },
    toLoginScreen: () -> Unit,
    toMyPageScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LogoutScreen(
        updater = viewModel,
        toMyPageScreen = toMyPageScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, toLoginScreen, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: LogoutViewModel,
    toLoginScreen: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.toLoginScreenEvent.collect {
            toLoginScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.logoutErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to get todo list",
                withDismissAction = true,
            )
        }
    }
}

interface LogoutUiUpdater {
    suspend fun logout()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutScreen(
    updater: LogoutUiUpdater,
    toMyPageScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Logout") },
                navigationIcon = {
                    IconButton(onClick = toMyPageScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            onLogoutConfirmed = { scope.launch { updater.logout() } },
            onLogoutCancelled = toMyPageScreen,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    onLogoutConfirmed: () -> Unit,
    onLogoutCancelled: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            ConfirmedButton(onLogoutConfirmed)
            Spacer(modifier = Modifier.width(16.dp))
            CancelledButton(onLogoutCancelled)
        }
    }
}

@Composable
private fun Header() {
    Text("Do you want to logout?", style = MaterialTheme.typography.headlineMedium)
}

@Composable
private fun RowScope.ConfirmedButton(onLogoutConfirmed: () -> Unit) {
    Button(
        onClick = onLogoutConfirmed,
        modifier = Modifier.weight(1f)
    ) {
        Text("Yes")
    }
}

@Composable
private fun RowScope.CancelledButton(onLogoutCancelled: () -> Unit) {
    Button(
        onClick = onLogoutCancelled,
        colors = ButtonDefaults.buttonColors().copy(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = Modifier.weight(1f)
    ) {
        Text("No")
    }
}