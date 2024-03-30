package com.ring.ring.usecase.todo

import com.ring.ring.data.session.SessionRepository
import com.ring.ring.data.todo.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditTodoDone(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<EditTodoDone.Req, EditTodoDone.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        todoRepository.editDone(req.id, req.done, session)
        return@withContext Res()
    }

    data class Req(
        val id: Long,
        val done: Boolean,
    ) : UseCase.Req

    class Res : UseCase.Res
}