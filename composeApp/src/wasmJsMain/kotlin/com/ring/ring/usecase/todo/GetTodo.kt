package com.ring.ring.usecase.todo

import com.ring.ring.data.Todo
import com.ring.ring.di.DataModules
import com.ring.ring.repository.SessionRepository
import com.ring.ring.repository.TodoRepository
import com.ring.ring.ui.todo.edit.EditTodoUiState
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

class GetTodo(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        val todo = todoRepository.get(req.todoId, session)
        return@withContext todo.toGetTodoElement()
    }

    data class Req(
        val todoId: Long
    ) : UseCase.Req

    data class Res(
        val id: Long,
        val title: String,
        val description: String,
        val done: Boolean,
        val deadline: EditTodoUiState.Deadline,
    ) : UseCase.Res

    private fun Todo.toGetTodoElement() = Res(
        id = id ?: throw IllegalArgumentException(),
        title = title,
        description = description,
        done = done,
        deadline = EditTodoUiState.Deadline(
            deadline.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
        ),
    )
}