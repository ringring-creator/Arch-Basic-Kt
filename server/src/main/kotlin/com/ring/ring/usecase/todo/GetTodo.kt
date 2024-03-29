package com.ring.ring.usecase.todo

import com.ring.ring.data.Session
import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.serialization.Serializable

class GetTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val todo = repository.get(req.todoId)
        return Res(todo = todo.toGetTodo())
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

    @Serializable
    data class Res(
        val todo: Todo,
    ) : UseCase.Res {
        @Serializable
        data class Todo(
            val id: String,
            val title: String,
            val description: String,
            val done: String,
            val deadline: String,
            val userId: String,
        )
    }
}