package com.ring.ring.usecase.todo

import com.ring.ring.data.Todo
import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.exception.BadRequestException
import com.ring.ring.usecase.UseCase
import kotlinx.datetime.LocalDate

class EditTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<EditTodo.Req, EditTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        repository.save(
            todo = req.toTodo()
        )
        return Res()
    }

    data class Req(
        val id: String,
        val title: String,
        val description: String,
        val done: Boolean,
        val deadline: LocalDate,
        val userId: Long,
    ) : UseCase.Req {
        fun toTodo(): Todo = Todo(
            id = id.toLongOrNull() ?: throw BadRequestException(message = "Id is invalid"),
            title = title,
            description = description,
            done = done,
            deadline = deadline,
            userId = userId,
        )
    }

    class Res : UseCase.Res
}