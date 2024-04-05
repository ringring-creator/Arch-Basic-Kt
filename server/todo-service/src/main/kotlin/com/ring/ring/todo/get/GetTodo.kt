package com.ring.ring.todo.get

import com.ring.ring.todo.shared.*
import kotlinx.serialization.Serializable

internal class GetTodo(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val repository: GetTodoRepository = GetTodoModules.getTodoRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val todo = repository.get(req.todoId)
        return Res(todo = todo.toGetTodo())
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

    @Serializable
    data class Res(
        val todo: ResTodo,
    ) : UseCase.Res {
        @Serializable
        data class ResTodo(
            val id: String,
            val title: String,
            val description: String,
            val done: String,
            val deadline: String,
            val userId: String,
        )
    }

    private fun Todo.toGetTodo(): Res.ResTodo = Res.ResTodo(
        id = id?.toString() ?: throw IllegalStateException(),
        title = title,
        description = description,
        done = done.toString(),
        deadline = deadline.toString(),
        userId = userId.toString(),
    )

}