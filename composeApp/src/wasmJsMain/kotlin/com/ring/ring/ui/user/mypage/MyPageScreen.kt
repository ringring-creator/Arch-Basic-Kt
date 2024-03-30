package com.ring.ring.ui.user.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = remember { MyPageViewModel() },
    toLogoutScreen: () -> Unit,
    toEditUserScreen: () -> Unit,
    toWithdrawalUserScreen: () -> Unit,
    toTodoListScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    MyPageScreen(
        uiState = MyPageViewModel.rememberLoginUiState(viewModel),
        toLogoutScreen = toLogoutScreen,
        toEditUserScreen = toEditUserScreen,
        toWithdrawalUserScreen = toWithdrawalUserScreen,
        toTodoListScreen = toTodoListScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: MyPageViewModel,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.getUser()
    }
    LaunchedEffect(Unit) {
        viewModel.getUserErrorEvent.collect {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = "Failed to get user",
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

data class MyPageUiState(
    val email: String,
    val logoutEnabled: Boolean,
    val editEnabled: Boolean,
    val withdrawalEnabled: Boolean,
)

@Composable
fun MyPageScreen(
    uiState: MyPageUiState,
    toLogoutScreen: () -> Unit,
    toEditUserScreen: () -> Unit,
    toWithdrawalUserScreen: () -> Unit,
    toTodoListScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    Scaffold(
        topBar = { TodoNavBar(toTodoListScreen = toTodoListScreen) },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { paddingValues ->
            Content(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                toLogoutScreen = toLogoutScreen,
                toEditUserScreen = toEditUserScreen,
                toWithdrawalUserScreen = toWithdrawalUserScreen,
            )
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: MyPageUiState,
    toLogoutScreen: () -> Unit,
    toEditUserScreen: () -> Unit,
    toWithdrawalUserScreen: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Header()
        EmailText(uiState)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LogoutButton(toLogoutScreen, uiState.logoutEnabled)
            EditButton(toEditUserScreen, uiState.editEnabled)
            WithdrawalButton(toWithdrawalUserScreen, uiState.withdrawalEnabled)
        }
    }
}

@Composable
private fun Header() {
    Text("My Page", style = MaterialTheme.typography.headlineMedium)
}

@Composable
private fun EmailText(uiState: MyPageUiState) {
    Text("Email: ${uiState.email}")
}

@Composable
private fun LogoutButton(toLogoutScreen: () -> Unit, enabled: Boolean) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = toLogoutScreen,
        enabled = enabled
    ) { Text("Logout") }
}

@Composable
private fun EditButton(toEditUserScreen: () -> Unit, enabled: Boolean) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = toEditUserScreen,
        enabled = enabled
    ) { Text("Edit") }
}

@Composable
private fun WithdrawalButton(
    toWithdrawalUserScreen: () -> Unit,
    enabled: Boolean,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = toWithdrawalUserScreen,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
    ) { Text("Withdrawal") }
}

@Composable
private fun TodoNavBar(
    toTodoListScreen: () -> Unit,
) {
    NavigationBar {
        NavigationRailItem(
            icon = { },
            label = { Text("Todo") },
            selected = false,
            onClick = toTodoListScreen
        )
        NavigationRailItem(
            icon = { },
            label = { Text("My Page") },
            selected = true,
            onClick = {}
        )
    }
}