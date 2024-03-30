package com.ring.ring.usecase.todo

import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class EditTodoDone(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<EditTodoDone.Req, EditTodoDone.Res>() {
    override suspend fun execute(req: Req): Res {
        repository.updateDone(
            id = req.todoId,
            done = req.done,
        )
        return Res()
    }

    @Serializable
    data class Req(
        val todoId: Long,
        val done: Boolean,
        val session: Session
    ) : UseCase.Req {
        @Serializable
        data class Session(
            val userId: Long,
            val credential: String,
        )
    }

    class Res : UseCase.Res
}