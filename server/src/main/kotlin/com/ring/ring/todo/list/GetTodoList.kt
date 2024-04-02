package com.ring.ring.todo.list

import com.ring.ring.todo.Todo
import com.ring.ring.todo.UseCase
import com.ring.ring.validate.ValidateSession
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class GetTodoList(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: ListTodoRepository = ListTodoModules.listTodoRepository,
) : UseCase<GetTodoList.Req, GetTodoList.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        val todoList = repository.list(req.session.userId)
        return Res(todoList = todoList.map { it.toGetTodoListItem() })
    }

    @Serializable
    data class Req(
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req

    @Serializable
    data class Res(
        val todoList: List<ResTodo>
    ) : UseCase.Res {
        @Serializable
        data class ResTodo(
            val id: Long,
            val title: String,
            val description: String,
            val done: Boolean,
            val deadline: LocalDate,
            val userId: Long,
        )
    }

    private fun Todo.toGetTodoListItem(): Res.ResTodo = Res.ResTodo(
        id = id ?: throw IllegalStateException(),
        title = title,
        description = description,
        done = done,
        deadline = deadline,
        userId = userId,
    )
}