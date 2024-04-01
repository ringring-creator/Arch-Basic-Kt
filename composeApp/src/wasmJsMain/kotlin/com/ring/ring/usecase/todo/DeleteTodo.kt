package com.ring.ring.usecase.todo

import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.data.repository.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteTodo(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        todoRepository.delete(req.id, session)
        return@withContext Res()
    }

    data class Req(
        val id: Long,
    ) : UseCase.Req

    class Res : UseCase.Res
}