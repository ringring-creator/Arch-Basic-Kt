package com.ring.ring.todo.get

import com.ring.ring.data.db.Todo
import com.ring.ring.todo.shared.UseCase
import com.ring.ring.todo.shared.ValidateSession
import kotlinx.serialization.Serializable

internal class GetTodo(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: GetTodoRepository = GetTodoModules.getTodoRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val todo = repository.get(req.todoId)
        return Res(todo = todo.toGetTodo())
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val session: ValidateSession.ReqSession,
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