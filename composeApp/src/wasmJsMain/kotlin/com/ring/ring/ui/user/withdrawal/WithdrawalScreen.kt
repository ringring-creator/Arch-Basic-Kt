package com.ring.ring.ui.user.withdrawal


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
fun WithdrawalScreen(
    viewModel: WithdrawalViewModel = remember { WithdrawalViewModel() },
    toLoginScreen: () -> Unit,
    toMyPageScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    WithdrawalScreen(
        updater = viewModel,
        toMyPageScreen = toMyPageScreen,
        snackBarHostState = snackBarHostState,
    )

    LaunchedEffect(Unit) {
        viewModel.toLoginScreenEvent.collect {
            toLoginScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.withdrawalErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to withdrawal",
                withDismissAction = true,
            )
        }
    }
}

interface WithdrawalUiUpdater {
    suspend fun withdrawal()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawalScreen(
    updater: WithdrawalUiUpdater,
    toMyPageScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Withdrawal") },
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
            onWithdrawalConfirmed = {
                scope.launch { updater.withdrawal() }
            },
            onWithdrawalCancelled = toMyPageScreen,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    onWithdrawalConfirmed: () -> Unit,
    onWithdrawalCancelled: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Do you want to withdrawal?", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Button(
                onClick = onWithdrawalConfirmed,
                modifier = Modifier.weight(1f)
            ) {
                Text("Yes")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onWithdrawalCancelled,
                colors = ButtonDefaults.buttonColors().copy(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.weight(1f)
            ) {
                Text("No")
            }
        }
    }
}