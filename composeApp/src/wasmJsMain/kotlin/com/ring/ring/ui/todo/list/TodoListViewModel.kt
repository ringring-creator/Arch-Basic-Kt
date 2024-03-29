package com.ring.ring.ui.todo.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoListViewModel(
    private val getTodoListUseCase: GetTodoList = GetTodoList()
) {
    private val _todoList: MutableStateFlow<List<TodoListUiState.Todo>> = MutableStateFlow(emptyList())
    val todoList = _todoList.asStateFlow()

    suspend fun getTodoList() {
        val res = getTodoListUseCase(GetTodoList.Req())
        _todoList.value = res.todoList.map(
            GetTodoList.Res.Todo::toTodoListUiStateTodo
        )
    }

    companion object {
        @Composable
        fun rememberTodoListUiState(viewModel: TodoListViewModel): TodoListUiState {
            val todos by viewModel.todoList.collectAsState()
            return TodoListUiState(
                todos = todos
            )
        }
    }
}