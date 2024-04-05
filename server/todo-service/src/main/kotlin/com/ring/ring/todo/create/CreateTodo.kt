package com.ring.ring.todo.create

import com.ring.ring.todo.shared.*
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

internal class CreateTodo(
    private val sessionRepository: ValidateSessionRepository = SharedModules.validateSessionRepository,
    private val repository: CreateTodoRepository = CreateTodoModules.createTodoRepository,
) : UseCase<CreateTodo.Req, CreateTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        validateSession(req.session)
        repository.save(todo = req.todo.toTodo())
        return Res()
    }


    private suspend fun validateSession(session: Session) {
        val isInvalid = sessionRepository.validate(session = session).not()
        if (isInvalid) throw NotLoggedInException("session is invalid")
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