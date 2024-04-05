package com.ring.ring.todo.delete

import com.ring.ring.todo.shared.*
import kotlinx.serialization.Serializable

internal class DeleteTodo(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val repository: DeleteTodoRepository = DeleteTodoModules.deleteTodoRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.delete(req.todoId)
        return Res()
    }

    private suspend fun validateSession(session: Session) {
        val isInvalid = sessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}