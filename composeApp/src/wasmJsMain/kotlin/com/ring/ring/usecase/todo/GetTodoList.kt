package com.ring.ring.usecase.todo

import com.ring.ring.data.Todo
import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.data.repository.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.ui.todo.list.TodoListUiState
import com.ring.ring.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTodoList(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<GetTodoList.Req, GetTodoList.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        val todoList = todoRepository.list(session)
        return@withContext Res(
            todoList = todoList.map { it.toResTodo() }
        )
    }

    class Req : UseCase.Req

    data class Res(val todoList: List<Todo>) : UseCase.Res {
        data class Todo(
            val id: Long,
            val title: String,
            val done: Boolean,
            val deadline: String,
        ) {
            fun toTodoListUiStateTodo() = TodoListUiState.Todo(
                id = id,
                title = title,
                done = done,
                deadline = deadline,
            )
        }
    }

    private fun Todo.toResTodo() = Res.Todo(
        id = id ?: throw IllegalArgumentException(),
        title = title,
        done = done,
        deadline = deadline.toString(),
    )
}