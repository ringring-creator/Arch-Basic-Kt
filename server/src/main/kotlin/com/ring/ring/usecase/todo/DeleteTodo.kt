package com.ring.ring.usecase.todo

import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class DeleteTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val id = req.todoId
        repository.delete(id)
        return Res()
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val session: Session,
    ) : UseCase.Req {
        @Serializable
        data class Session(
            val userId: Long,
            val credential: String,
        )
    }

    class Res : UseCase.Res
}