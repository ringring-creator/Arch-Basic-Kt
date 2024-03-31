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
    private val uiState: UiState = UiState.init()

    private val _toTodoListEvent = Channel<Unit>()
    val toTodoListEvent = _toTodoListEvent.receiveAsFlow()

    private val _saveTodoErrorEvent = Channel<Unit>()
    val saveTodoErrorEvent = _saveTodoErrorEvent.receiveAsFlow()

    override suspend fun saveTodo() {
        try {
            createTodo(uiState.toCreateTodoReq())
            _toTodoListEvent.trySend(Unit)
        } catch (e: Throwable) {
            _saveTodoErrorEvent.trySend(Unit)
        }
    }

    override fun setTitle(title: String) {
        uiState.setTitle(title)
    }

    override fun setDescription(description: String) {
        uiState.setDescription(description)
    }

    override fun setDone(done: Boolean) {
        uiState.setDone(done)
    }

    override fun setDeadline(dateMillis: Long) {
        uiState._deadline.value = CreateTodoUiState.Deadline(dateMillis)
    }

    override fun showDatePicker() {
        uiState.setIsShowDatePicker(true)
    }

    override fun dismissDatePicker() {
        uiState.setIsShowDatePicker(false)
    }

    @Composable
    fun rememberCreateTodoUiState(): CreateTodoUiState {
        val title by uiState.title.collectAsState()
        val description by uiState.description.collectAsState()
        val done by uiState.done.collectAsState()
        val deadline by uiState.deadline.collectAsState()
        val showDatePicker by uiState.isShowDatePicker.collectAsState()
        return CreateTodoUiState(
            title = title,
            description = description,
            done = done,
            deadline = deadline,
            isShowDatePicker = showDatePicker,
        )
    }

    private class UiState(
        val _title: MutableStateFlow<String>,
        val _description: MutableStateFlow<String>,
        val _done: MutableStateFlow<Boolean>,
        val _deadline: MutableStateFlow<CreateTodoUiState.Deadline>,
        val _isShowDatePicker: MutableStateFlow<Boolean>,
    ) {
        val title = _title.asStateFlow()
        val description = _description.asStateFlow()
        val done = _done.asStateFlow()
        val deadline = _deadline.asStateFlow()
        val isShowDatePicker = _isShowDatePicker.asStateFlow()

        fun toCreateTodoReq() = CreateTodo.Req(
            title = title.value,
            description = description.value,
            done = done.value,
            deadline = deadline.value.dateMillis,
        )

        fun setTitle(title: String) {
            if (this.title.value == title) return
            _title.value = title
        }

        fun setDescription(description: String) {
            if (this.description.value == description) return
            _description.value = description
        }

        fun setDone(done: Boolean) {
            if (this.done.value == done) return
            _done.value = done
        }

        fun setIsShowDatePicker(isShowDatePicker: Boolean) {
            if (this.isShowDatePicker.value == isShowDatePicker) return
            _isShowDatePicker.value = isShowDatePicker
        }

        companion object {
            fun init(): UiState {
                return UiState(
                    _title = MutableStateFlow(""),
                    _description = MutableStateFlow(""),
                    _done = MutableStateFlow(false),
                    _deadline = MutableStateFlow(CreateTodoUiState.Deadline.currentTime()),
                    _isShowDatePicker = MutableStateFlow(false),
                )
            }
        }
    }
}