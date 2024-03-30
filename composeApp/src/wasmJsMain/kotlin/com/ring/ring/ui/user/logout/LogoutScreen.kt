package com.ring.ring.ui.user.logout


import androidx.compose.foundation.layout.*
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
    LogoutScreen(
        updater = viewModel,
        toMyPageScreen = toMyPageScreen,
    )

    LaunchedEffect(Unit) {
        viewModel.toLoginScreenEvent.collect {
            toLoginScreen()
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
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Logout") }) }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            onLogoutConfirmed = {
                scope.launch { updater.logout() }
            },
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
        Text("Do you want to logout?", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Button(
                onClick = onLogoutConfirmed,
                modifier = Modifier.weight(1f)
            ) {
                Text("Yes")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onLogoutCancelled,
                colors = ButtonDefaults.buttonColors().copy(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.weight(1f)
            ) {
                Text("No")
            }
        }
    }
}