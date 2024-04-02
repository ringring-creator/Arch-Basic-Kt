package com.ring.ring.todo.delete

import com.ring.ring.todo.UseCase
import com.ring.ring.todo.ValidateSession
import kotlinx.serialization.Serializable

class DeleteTodo(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: DeleteTodoRepository = DeleteTodoModules.deleteTodoRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.delete(req.todoId)
        return Res()
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    class Res : UseCase.Res
}