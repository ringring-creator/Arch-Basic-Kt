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
    MyPageScreen(
        uiState = MyPageViewModel.rememberLoginUiState(viewModel),
        toLogoutScreen = toLogoutScreen,
        toEditUserScreen = toEditUserScreen,
        toWithdrawalUserScreen = toWithdrawalUserScreen,
        toTodoListScreen = toTodoListScreen,
    )

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }
}

data class MyPageUiState(
    val email: String,
)

@Composable
fun MyPageScreen(
    uiState: MyPageUiState,
    toLogoutScreen: () -> Unit,
    toEditUserScreen: () -> Unit,
    toWithdrawalUserScreen: () -> Unit,
    toTodoListScreen: () -> Unit,
) {
    Scaffold(
        topBar = { TodoNavBar(toTodoListScreen = toTodoListScreen) },
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Page", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Email: ${uiState.email}")
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = toLogoutScreen
            ) { Text("Logout") }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = toEditUserScreen
            ) { Text("Edit") }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = toWithdrawalUserScreen,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) { Text("Withdrawal") }
        }
    }
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