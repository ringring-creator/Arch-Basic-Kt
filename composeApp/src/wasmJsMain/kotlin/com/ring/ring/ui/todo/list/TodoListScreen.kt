package com.ring.ring.ui.todo.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
fun TodoListScreen(
    viewModel: TodoListViewModel = remember { TodoListViewModel() },
    toCreateTodoScreen: () -> Unit,
    toEditTodoScreen: (Long) -> Unit,
    toMyPageScreen: () -> Unit,
) {
    val uiState = viewModel.rememberTodoListUiState()
    val snackBarHostState = remember { SnackbarHostState() }

    TodoListScreen(
        uiState = uiState,
        updater = viewModel,
        toCreateTodoScreen = toCreateTodoScreen,
        toEditTodoScreen = toEditTodoScreen,
        toMyPageScreen = toMyPageScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: TodoListViewModel,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.getTodoList()
    }
    LaunchedEffect(Unit) {
        viewModel.toggleDoneErrorEvent.collect {
            snackBarHostState.showSnackbar(
                "Failed to update done",
                withDismissAction = true
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getTodoListErrorEvent.collect {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = "Failed to get todo list",
                actionLabel = "Retry",
                withDismissAction = true,
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.getTodoList()
                }
            }
        }
    }
}

data class TodoListUiState(
    val todos: List<Todo>
) {
    data class Todo(
        val id: Long,
        val title: String,
        val done: Boolean,
        val deadline: String,
    )
}

interface TodoListUiUpdater {
    suspend fun toggleDone(todoId: Long)
}

@Composable
fun TodoListScreen(
    uiState: TodoListUiState,
    updater: TodoListUiUpdater,
    toCreateTodoScreen: () -> Unit,
    toEditTodoScreen: (Long) -> Unit,
    toMyPageScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = { TodoNavBar(toMyPageScreen) },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = toCreateTodoScreen) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        val modifier = Modifier.padding(it)
        Content(modifier, uiState, toEditTodoScreen, scope, updater)
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: TodoListUiState,
    toEditTodoScreen: (Long) -> Unit,
    scope: CoroutineScope,
    updater: TodoListUiUpdater
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        uiState.todos.forEach { todo ->
            Item(toEditTodoScreen, todo, scope, updater)
        }
    }
}

@Composable
private fun TodoNavBar(
    toMyPageScreen: () -> Unit,
) {
    NavigationBar {
        NavigationBar {
            NavigationRailItem(
                icon = { },
                label = { Text("Todo") },
                selected = true,
                onClick = {}
            )
            NavigationRailItem(
                icon = { },
                label = { Text("My Page") },
                selected = false,
                onClick = toMyPageScreen
            )
        }
    }
}

@Composable
private fun Header() {
    Text("Todo List", style = MaterialTheme.typography.headlineLarge)
}

@Composable
private fun Item(
    toEditTodoScreen: (Long) -> Unit,
    todo: TodoListUiState.Todo,
    scope: CoroutineScope,
    updater: TodoListUiUpdater
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = { toEditTodoScreen(todo.id) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DoneCheckBox(todo.done) {
                scope.launch { updater.toggleDone(todo.id) }
            }
            TitleText(todo.title)
            Spacer(modifier = Modifier.weight(1f))
            DeadlineText(todo.deadline)
        }
    }
}

@Composable
private fun DoneCheckBox(
    done: Boolean,
    onCheckedChange: () -> Unit,
) {
    Checkbox(
        checked = done,
        onCheckedChange = { onCheckedChange() },
        modifier = Modifier.padding(end = 8.dp)
    )
}

@Composable
private fun TitleText(title: String) {
    Text(title, style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun DeadlineText(deadline: String) {
    Text("Deadline: $deadline", style = MaterialTheme.typography.bodyMedium)
}
