package com.ring.ring.usecase.todo

import com.ring.ring.data.Todo
import com.ring.ring.data.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class GetTodoList(
    private val repository: TodoRepository = DataModules.todoRepository,
) : UseCase<GetTodoList.Req, GetTodoList.Res>() {
    override suspend fun execute(req: Req): Res {
        val todoList = repository.list(req.userId)
        return Res(todoList = todoList.mapNotNull(Todo::toGetTodoListItem))
    }

    @Serializable
    data class Req(
        val userId: Long,
        val credential: String,
    ) : UseCase.Req

    @Serializable
    data class Res(
        val todoList: List<Todo>
    ) : UseCase.Res {
        @Serializable
        data class Todo(
            val id: Long,
            val title: String,
            val description: String,
            val done: Boolean,
            val deadline: LocalDate,
            val userId: Long,
        )
    }
}