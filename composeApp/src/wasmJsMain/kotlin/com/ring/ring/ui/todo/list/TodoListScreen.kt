package com.ring.ring.ui.todo.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = remember { TodoListViewModel() },
    toCreateTodoScreen: () -> Unit,
    toEditTodoScreen: (Long) -> Unit,
) {
    val uiState = TodoListViewModel.rememberTodoListUiState(viewModel)
    TodoListScreen(
        uiState = uiState,
        toCreateTodoScreen = toCreateTodoScreen,
        toEditTodoScreen = toEditTodoScreen,
    )

    LaunchedEffect(Unit) {
        viewModel.getTodoList()
    }
}

data class TodoListUiState(
    val todos: List<Todo>
) {
    data class Todo(
        val id: String,
        val title: String,
        val done: Boolean,
        val deadline: String,
    )
}

@Composable
fun TodoListScreen(
    uiState: TodoListUiState,
    toCreateTodoScreen: () -> Unit,
    toEditTodoScreen: (Long) -> Unit,
) {
    Scaffold(
        topBar = { TodoNavBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = toCreateTodoScreen) {
                Icon(Icons.Filled.Add, contentDescription = "Create New Todo")
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
                    onClick = { toEditTodoScreen(todo.id.toLong()) }
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = todo.done,
                            onCheckedChange = { /* Handle check change */ },
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
fun TodoNavBar() {
}
