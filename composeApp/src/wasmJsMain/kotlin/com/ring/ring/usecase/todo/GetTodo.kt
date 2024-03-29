package com.ring.ring.usecase.todo

import com.ring.ring.data.session.SessionRepository
import com.ring.ring.data.todo.Todo
import com.ring.ring.data.todo.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.ui.todo.edit.EditTodoUiState
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTodo(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.getSession() ?: throw Exception()
        val todo = todoRepository.get(req.todoId, session)
        return@withContext Res(
            todo = todo.toGetTodoElement()
        )
    }

    data class Req(
        val todoId: Long
    ) : UseCase.Req

    data class Res(val todo: Todo) : UseCase.Res {
        data class Todo(
            val id: Long,
            val title: String,
            val description: String,
            val done: Boolean,
            val deadline: EditTodoUiState.Deadline,
        )
    }
}