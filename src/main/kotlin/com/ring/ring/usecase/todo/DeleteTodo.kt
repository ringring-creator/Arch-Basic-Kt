package com.ring.ring.usecase.todo

import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.exception.BadRequestException
import com.ring.ring.usecase.UseCase

class DeleteTodo(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<DeleteTodo.Req, DeleteTodo.Res>() {
    override suspend fun execute(req: Req): Res {
        val id = req.id.toLongOrNull() ?: throw BadRequestException(message = "Id is invalid")
        repository.delete(id)
        return Res()
    }

    data class Req(
        val id: String,
    ) : UseCase.Req

    class Res : UseCase.Res
}