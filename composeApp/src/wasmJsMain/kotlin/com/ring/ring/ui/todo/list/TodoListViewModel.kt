package com.ring.ring.ui.todo.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.EditTodoDone
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoListViewModel(
    private val getTodoListUseCase: GetTodoList = GetTodoList(),
    private val editTodoDone: EditTodoDone = EditTodoDone(),
) : TodoListUiUpdater {
    companion object {
        @Composable
        fun rememberTodoListUiState(viewModel: TodoListViewModel): TodoListUiState {
            val todos by viewModel.todoList.collectAsState()
            return TodoListUiState(
                todos = todos
            )
        }
    }

    private val _todoList: MutableStateFlow<List<TodoListUiState.Todo>> = MutableStateFlow(emptyList())
    val todoList = _todoList.asStateFlow()

    suspend fun getTodoList() {
        val res = getTodoListUseCase(GetTodoList.Req())
        _todoList.value = res.todoList.map(
            GetTodoList.Res.Todo::toTodoListUiStateTodo
        )
    }

    override suspend fun toggleDone(todoId: Long) {
        val index = findTargetIndex(todoId)
        val newTodo = getTodoWithToggleDone(index)
        editTodoDone(EditTodoDone.Req(newTodo.id, newTodo.done))
        updateTodoList(index, newTodo)
    }

    private fun findTargetIndex(todoId: Long) = todoList.value.indexOfFirst { it.id == todoId }

    private fun getTodoWithToggleDone(index: Int) = todoList.value[index].copy(done = todoList.value[index].done.not())

    private fun updateTodoList(index: Int, newTodo: TodoListUiState.Todo) {
        val newList = todoList.value.toMutableList()
        newList[index] = newTodo
        _todoList.value = newList
    }
}