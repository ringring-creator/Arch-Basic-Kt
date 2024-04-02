package com.ring.ring.todo.edit

import com.ring.ring.todo.Todo
import com.ring.ring.todo.UseCase
import com.ring.ring.todo.shared.ValidateSessionRepository
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class EditTodo(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val repository: EditTodoRepository = EditTodoModules.editTodoRepository,
) : UseCase<EditTodo.Req, EditTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
        repository.save(todo = req.toTodo())
        return Res()
    }

    @Serializable
    data class Req(
        val todo: ReqTodo,
        val session: Session,
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