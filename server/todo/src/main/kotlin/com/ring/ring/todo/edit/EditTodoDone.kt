package com.ring.ring.todo.edit

import com.ring.ring.todo.UseCase
import com.ring.ring.todo.shared.ValidateSessionRepository
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import kotlinx.serialization.Serializable

class EditTodoDone(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val repository: EditTodoRepository = EditTodoModules.editTodoRepository,
) : UseCase<EditTodoDone.Req, EditTodoDone.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
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
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}