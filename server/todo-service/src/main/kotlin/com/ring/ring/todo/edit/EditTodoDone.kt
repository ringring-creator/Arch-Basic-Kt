package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.*
import kotlinx.serialization.Serializable

internal class EditTodoDone(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
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

    private suspend fun validateSession(session: Session) {
        val isInvalid = sessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val done: Boolean,
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}