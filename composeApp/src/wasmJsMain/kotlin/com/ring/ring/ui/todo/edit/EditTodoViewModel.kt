package com.ring.ring.ui.todo.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.EditTodo
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class EditTodoViewModel(
    private val getTodoUseCase: GetTodo = GetTodo(),
    private val editTodoUseCase: EditTodo = EditTodo(),
) : EditTodoUiUpdater {
    private var id: Long? = null
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()
    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()
    private val _done = MutableStateFlow(false)
    val done = _done.asStateFlow()
    private val _deadline = MutableStateFlow(EditTodoUiState.Deadline.currentTime())
    val deadline = _deadline.asStateFlow()
    private val _isShowDatePicker = MutableStateFlow(false)
    val isShowDatePicker = _isShowDatePicker.asStateFlow()
    private val _toTodoListEvent = Channel<Unit>()
    val toTodoListEvent = _toTodoListEvent.receiveAsFlow()

    override fun onBack() {
        TODO("Not yet implemented")
    }

    override suspend fun editTodo() {
        val id = id ?: return
        editTodoUseCase(
            EditTodo.Req(
                id = id,
                title = title.value,
                description = description.value,
                done = done.value,
                deadline = deadline.value.dateMillis,
            )
        )
        _toTodoListEvent.trySend(Unit)
    }

    override suspend fun deleteTodo() {
    }

    override fun setTitle(title: String) {
        if (this.title.value == title) return
        _title.value = title
    }

    override fun setDescription(description: String) {
        if (this.description.value == description) return
        _description.value = description
    }

    override fun setDone(done: Boolean) {
        if (this.done.value == done) return
        _done.value = done
    }

    override fun setDeadline(dateMillis: Long) {
        _deadline.value = EditTodoUiState.Deadline(dateMillis)
    }

    override fun showDatePicker() {
        if (this.isShowDatePicker.value) return
        _isShowDatePicker.value = true

    }

    override fun dismissDatePicker() {
        if (this.isShowDatePicker.value.not()) return
        _isShowDatePicker.value = false
    }

    suspend fun getTodo(todoId: Long) {
        val res = getTodoUseCase(GetTodo.Req(todoId))
        id = res.todo.id
        _title.value = res.todo.title
        _description.value = res.todo.description
        _done.value = res.todo.done
        _deadline.value = res.todo.deadline
    }

    companion object {
        @Composable
        fun rememberEditTodoUiState(viewModel: EditTodoViewModel): EditTodoUiState {
            val title by viewModel.title.collectAsState()
            val description by viewModel.description.collectAsState()
            val done by viewModel.done.collectAsState()
            val deadline by viewModel.deadline.collectAsState()
            val showDatePicker by viewModel.isShowDatePicker.collectAsState()
            return EditTodoUiState(
                title = title,
                description = description,
                done = done,
                deadline = deadline,
                isShowDatePicker = showDatePicker,
            )
        }
    }
}