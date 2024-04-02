package com.ring.ring.todo.create

import com.ring.ring.todo.Todo
import com.ring.ring.todo.UseCase
import com.ring.ring.todo.shared.ValidateSessionRepository
import com.ring.ring.user.shared.NotLoggedInException
import com.ring.ring.user.shared.Session
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class CreateTodo(
    private val sessionRepository: ValidateSessionRepository = ValidateSessionRepository(),
    private val repository: CreateTodoRepository = CreateTodoModules.createTodoRepository,
) : UseCase<CreateTodo.Req, CreateTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val isValid = sessionRepository.validate(req.session)
        if (isValid.not()) throw NotLoggedInException()
        repository.save(todo = req.todo.toTodo())
        return Res()
    }

    @Serializable
    data class Req(
        val todo: ReqTodo,
        val session: Session,
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