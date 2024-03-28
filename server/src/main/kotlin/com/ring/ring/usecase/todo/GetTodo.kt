package com.ring.ring.usecase.todo

import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase

class GetTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<GetTodo.Req, GetTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val todo = repository.get(req.id)
        return Res(todo = todo.toGetTodo())
    }

    data class Req(
        val id: Long,
    ) : UseCase.Req

    data class Res(
        val todo: Todo,
    ) : UseCase.Res {
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