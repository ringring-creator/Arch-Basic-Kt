package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.UseCase
import com.ring.ring.todo.shared.ValidateSession
import kotlinx.serialization.Serializable

internal class EditTodoDone(
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