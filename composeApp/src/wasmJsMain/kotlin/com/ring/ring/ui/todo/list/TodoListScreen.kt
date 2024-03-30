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
    val uiState = TodoListViewModel.rememberTodoListUiState(viewModel)
    TodoListScreen(
        uiState = uiState,
        updater = viewModel,
        toCreateTodoScreen = toCreateTodoScreen,
        toEditTodoScreen = toEditTodoScreen,
        toMyPageScreen = toMyPageScreen,
    )

    LaunchedEffect(Unit) {
        viewModel.getTodoList()
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
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = { TodoNavBar(toMyPageScreen) },
        floatingActionButton = {
            FloatingActionButton(onClick = toCreateTodoScreen) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Todo List", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            uiState.todos.forEach { todo ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = { toEditTodoScreen(todo.id) }
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = todo.done,
                            onCheckedChange = {
                                scope.launch { updater.toggleDone(todo.id) }
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(todo.title, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        Text("Deadline: ${todo.deadline}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
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