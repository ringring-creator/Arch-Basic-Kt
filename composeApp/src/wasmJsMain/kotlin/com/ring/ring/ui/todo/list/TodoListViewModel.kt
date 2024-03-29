package com.ring.ring.ui.todo.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoListViewModel {
    private val _todos: MutableStateFlow<List<TodoListUiState.Todo>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    companion object {
        @Composable
        fun rememberTodoListUiState(viewModel: TodoListViewModel): TodoListUiState {
            val todos by viewModel.todos.collectAsState()
            return TodoListUiState(
                todos = todos
            )
        }
    }
}