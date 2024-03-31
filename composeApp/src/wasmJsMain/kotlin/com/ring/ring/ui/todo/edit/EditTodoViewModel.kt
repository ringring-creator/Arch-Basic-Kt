package com.ring.ring.ui.todo.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ring.ring.usecase.todo.DeleteTodo
import com.ring.ring.usecase.todo.EditTodo
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class EditTodoViewModel(
    private val getTodoUseCase: GetTodo = GetTodo(),
    private val editTodoUseCase: EditTodo = EditTodo(),
    private val deleteTodoUseCase: DeleteTodo = DeleteTodo(),
) : EditTodoUiUpdater {
    private val uiState: UiState = UiState.init()

    private val _toTodoListEvent = Channel<Unit>()
    val toTodoListEvent = _toTodoListEvent.receiveAsFlow()

    private val _editErrorEvent = Channel<Unit>()
    val editErrorEvent = _editErrorEvent.receiveAsFlow()
    private val _deleteErrorEvent = Channel<Unit>()
    val deleteErrorEvent = _deleteErrorEvent.receiveAsFlow()
    private val _getTodoErrorEvent = Channel<Unit>()
    val getTodoErrorEvent = _getTodoErrorEvent.receiveAsFlow()

    suspend fun getTodo(todoId: Long) {
        val res = try {
            getTodoUseCase(GetTodo.Req(todoId))
        } catch (e: Throwable) {
            _getTodoErrorEvent.trySend(Unit)
            return
        }
        uiState.set(res)
    }

    override suspend fun editTodo() {
        try {
            editTodoUseCase(uiState.toEditTodoReq())
            _toTodoListEvent.trySend(Unit)
        } catch (e: Throwable) {
            _editErrorEvent.trySend(Unit)
        }
    }

    override suspend fun deleteTodo() {
        try {
            val id = uiState.unwrapId()
            deleteTodoUseCase(DeleteTodo.Req(id))
            _toTodoListEvent.trySend(Unit)
        } catch (e: Throwable) {
            _deleteErrorEvent.trySend(Unit)
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
        uiState._deadline.value = EditTodoUiState.Deadline(dateMillis)
    }

    override fun showDatePicker() {
        uiState.setIsShowDatePicker(true)
    }

    override fun dismissDatePicker() {
        uiState.setIsShowDatePicker(false)
    }

    @Composable
    fun rememberEditTodoUiState(): EditTodoUiState {
        val title by uiState.title.collectAsState()
        val description by uiState.description.collectAsState()
        val done by uiState.done.collectAsState()
        val deadline by uiState.deadline.collectAsState()
        val showDatePicker by uiState.isShowDatePicker.collectAsState()
        return EditTodoUiState(
            title = title,
            description = description,
            done = done,
            deadline = deadline,
            isShowDatePicker = showDatePicker,
        )
    }

    private data class UiState(
        var id: Long? = null,
        val _title: MutableStateFlow<String> = MutableStateFlow(""),
        val _description: MutableStateFlow<String> = MutableStateFlow(""),
        val _done: MutableStateFlow<Boolean> = MutableStateFlow(false),
        val _deadline: MutableStateFlow<EditTodoUiState.Deadline> = MutableStateFlow(EditTodoUiState.Deadline.currentTime()),
        val _isShowDatePicker: MutableStateFlow<Boolean> = MutableStateFlow(false),
    ) {
        val title = _title.asStateFlow()
        val description = _description.asStateFlow()
        val done = _done.asStateFlow()
        val deadline = _deadline.asStateFlow()
        val isShowDatePicker = _isShowDatePicker.asStateFlow()

        fun set(todo: GetTodo.Res) {
            id = todo.id
            setTitle(todo.title)
            setDescription(todo.description)
            setDone(todo.done)
            _deadline.value = todo.deadline
        }

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

        fun toEditTodoReq(): EditTodo.Req {
            return EditTodo.Req(
                id = unwrapId(),
                title = title.value,
                description = description.value,
                done = done.value,
                deadline = deadline.value.dateMillis,
            )
        }

        fun unwrapId() = id ?: run {
            throw IllegalStateException()
        }

        companion object {
            fun init(): UiState {
                return UiState(
                    id = null,
                    _title = MutableStateFlow(""),
                    _description = MutableStateFlow(""),
                    _done = MutableStateFlow(false),
                    _deadline = MutableStateFlow(EditTodoUiState.Deadline.currentTime()),
                    _isShowDatePicker = MutableStateFlow(false),
                )
            }
        }
    }
}