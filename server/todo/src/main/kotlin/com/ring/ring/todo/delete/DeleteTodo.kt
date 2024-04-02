package com.ring.ring.todo.delete

import com.ring.ring.todo.UseCase
import com.ring.ring.todo.shared.ValidateSessionRepository
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import kotlinx.serialization.Serializable

class DeleteTodo(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val repository: DeleteTodoRepository = DeleteTodoModules.deleteTodoRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
        repository.delete(req.todoId)
        return Res()
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val session: Session,
    ) : UseCase.Req

    class Res : UseCase.Res
}