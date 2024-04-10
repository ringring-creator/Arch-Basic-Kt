package com.ring.ring.usecase.todo

import com.ring.ring.di.DataModules
import com.ring.ring.repository.Todo
import com.ring.ring.repository.TodoRepository
import com.ring.ring.usecase.UseCase
import com.ring.ring.usecase.session.ValidateSession
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class GetTodoList(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: TodoRepository = DataModules.todoRepository,
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