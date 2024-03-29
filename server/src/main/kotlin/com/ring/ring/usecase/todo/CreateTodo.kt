package com.ring.ring.usecase.todo

import com.ring.ring.data.Todo
import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class CreateTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<CreateTodo.Req, CreateTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        repository.save(
            todo = req.toTodo()
        )
        return Res()
    }

    @Serializable
    data class Req(
        val title: String,
        val description: String,
        val done: Boolean,
        val deadline: LocalDate,
        val userId: Long,
    ) : UseCase.Req {
        fun toTodo(): Todo = Todo(
            id = null,
            title = title,
            description = description,
            done = done,
            deadline = deadline,
            userId = userId,
        )
    }

    class Res : UseCase.Res
}