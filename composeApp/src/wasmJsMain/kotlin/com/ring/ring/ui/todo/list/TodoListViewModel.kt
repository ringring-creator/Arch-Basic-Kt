package com.ring.ring.ui.todo.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.EditTodoDone
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class TodoListViewModel(
    private val getTodoListUseCase: GetTodoList = GetTodoList(),
    private val editTodoDone: EditTodoDone = EditTodoDone(),
) : TodoListUiUpdater {
    private val _todoList: MutableStateFlow<List<TodoListUiState.Todo>> = MutableStateFlow(emptyList())
    val todoList = _todoList.asStateFlow()

    private val _getTodoListErrorEvent = Channel<Unit>()
    val getTodoListErrorEvent = _getTodoListErrorEvent.receiveAsFlow()
    private val _toggleDoneErrorEvent = Channel<Unit>()
    val toggleDoneErrorEvent = _toggleDoneErrorEvent.receiveAsFlow()

    suspend fun getTodoList() {
        try {
            val res = getTodoListUseCase(GetTodoList.Req())
            _todoList.value = res.todoList.map(
                GetTodoList.Res.Todo::toTodoListUiStateTodo
            )
        } catch (e: Throwable) {
            _getTodoListErrorEvent.trySend(Unit)
        }
    }

    override suspend fun toggleDone(todoId: Long) {
        val index = findTargetIndex(todoId)
        val newTodo = getTodoWithToggleDone(index)
        try {
            editTodoDone(EditTodoDone.Req(newTodo.id, newTodo.done))
        } catch (e: Throwable) {
            _toggleDoneErrorEvent.trySend(Unit)
            return
        }
        updateTodoList(index, newTodo)
    }

    @Composable
    fun rememberTodoListUiState(): TodoListUiState {
        val todos by todoList.collectAsState()
        return TodoListUiState(
            todos = todos
        )
    }

    private fun findTargetIndex(todoId: Long) = todoList.value.indexOfFirst { it.id == todoId }

    private fun getTodoWithToggleDone(index: Int) = todoList.value[index].copy(done = todoList.value[index].done.not())

    private fun updateTodoList(index: Int, newTodo: TodoListUiState.Todo) {
        val newList = todoList.value.toMutableList()
        newList[index] = newTodo
        _todoList.value = newList
    }
}