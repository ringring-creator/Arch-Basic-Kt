package com.ring.ring.usecase.todo

import com.ring.ring.di.DataModules
import com.ring.ring.repository.TodoRepository
import com.ring.ring.usecase.UseCase
import com.ring.ring.usecase.session.ValidateSession
import kotlinx.serialization.Serializable

class EditTodoDone(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<EditTodoDone.Req, EditTodoDone.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.updateDone(
            id = req.todoId,
            done = req.done,
        )
        return Res()
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val done: Boolean,
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    class Res : UseCase.Res
}