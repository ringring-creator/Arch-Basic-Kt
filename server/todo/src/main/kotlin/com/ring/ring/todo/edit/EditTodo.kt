package com.ring.ring.todo.edit

import com.ring.ring.todo.Todo
import com.ring.ring.todo.UseCase
import com.ring.ring.todo.shared.ValidateSession
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

internal class EditTodo(
    private val validateSession: ValidateSession = ValidateSession(),
    private val repository: EditTodoRepository = EditTodoModules.editTodoRepository,
) : UseCase<EditTodo.Req, EditTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.save(todo = req.toTodo())
        return Res()
    }

    @Serializable
    data class Req(
        val todo: ReqTodo,
        val session: ValidateSession.ReqSession,
    ) : UseCase.Req {
        @Serializable
        data class ReqTodo(
            val id: Long?,
            val title: String,
            val description: String,
            val done: Boolean,
            val deadline: LocalDate,
            val userId: Long,
        )

        fun toTodo(): Todo = Todo(
            id = todo.id ?: throw BadRequestException(message = "Id is invalid"),
            title = todo.title,
            description = todo.description,
            done = todo.done,
            deadline = todo.deadline,
            userId = todo.userId,
        )
    }

    class Res : UseCase.Res
}