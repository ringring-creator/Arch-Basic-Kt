package com.ring.ring.todo.edit

import com.ring.ring.com.ring.ring.todo.UseCase
import com.ring.ring.com.ring.ring.todo.edit.EditTodoModules
import com.ring.ring.todo.ValidateSession
import kotlinx.serialization.Serializable

class EditTodoDone(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: EditTodoRepository = EditTodoModules.editTodoRepository,
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