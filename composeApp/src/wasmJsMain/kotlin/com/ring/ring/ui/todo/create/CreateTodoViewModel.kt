package com.ring.ring.ui.todo.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.CreateTodo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class CreateTodoViewModel(
    private val createTodo: CreateTodo = CreateTodo()
) : CreateTodoUiUpdater {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()
    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()
    private val _done = MutableStateFlow(false)
    val done = _done.asStateFlow()
    private val _deadline = MutableStateFlow(CreateTodoUiState.Deadline.currentTime())
    val deadline = _deadline.asStateFlow()
    private val _isShowDatePicker = MutableStateFlow(false)
    val isShowDatePicker = _isShowDatePicker.asStateFlow()
    private val _toTodoListEvent = Channel<Unit>()
    val toTodoListEvent = _toTodoListEvent.receiveAsFlow()
    private val _saveTodoErrorEvent = Channel<Unit>()
    val saveTodoErrorEvent = _saveTodoErrorEvent.receiveAsFlow()

    override suspend fun saveTodo() {
        try {
            createTodo(
                CreateTodo.Req(
                    title = title.value,
                    description = description.value,
                    done = done.value,
                    deadline = deadline.value.dateMillis,
                )
            )
            _toTodoListEvent.trySend(Unit)
        } catch (e: Throwable) {
            _saveTodoErrorEvent.trySend(Unit)
        }
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
        _deadline.value = CreateTodoUiState.Deadline(dateMillis)
    }

    override fun showDatePicker() {
        if (this.isShowDatePicker.value) return
        _isShowDatePicker.value = true
    }

    override fun dismissDatePicker() {
        if (this.isShowDatePicker.value.not()) return
        _isShowDatePicker.value = false
    }

    companion object {
        @Composable
        fun rememberCreateTodoUiState(viewModel: CreateTodoViewModel): CreateTodoUiState {
            val title by viewModel.title.collectAsState()
            val description by viewModel.description.collectAsState()
            val done by viewModel.done.collectAsState()
            val deadline by viewModel.deadline.collectAsState()
            val showDatePicker by viewModel.isShowDatePicker.collectAsState()
            return CreateTodoUiState(
                title = title,
                description = description,
                done = done,
                deadline = deadline,
                isShowDatePicker = showDatePicker,
            )
        }
    }
}