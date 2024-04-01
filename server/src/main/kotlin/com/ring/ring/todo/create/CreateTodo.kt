package com.ring.ring.todo.create

import com.ring.ring.di.DataModules
import com.ring.ring.session.validate.ValidateSession
import com.ring.ring.todo.Todo
import com.ring.ring.usecase.UseCase
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class CreateTodo(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: CreateTodoRepository = DataModules.createTodoRepository,
) : UseCase<CreateTodo.Req, CreateTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.save(todo = req.todo.toTodo())
        return Res()
    }

    @Serializable
    data class Req(
        val todo: ReqTodo,
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req {
        @Serializable
        data class ReqTodo(
            val title: String,
            val description: String,
            val done: Boolean,
            val deadline: LocalDate,
            val userId: Long,
        ) {
            fun toTodo(): Todo = Todo(
                id = null,
                title = title,
                description = description,
                done = done,
                deadline = deadline,
                userId = userId,
            )
        }
    }

    class Res : UseCase.Res
}