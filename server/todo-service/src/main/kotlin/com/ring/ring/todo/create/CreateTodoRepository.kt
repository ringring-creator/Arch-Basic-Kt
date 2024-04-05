package com.ring.ring.todo.create

import com.ring.ring.todo.shared.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import todo.shared.TodoQueries

internal class CreateTodoRepository(
    private val queries: TodoQueries,
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        queries.insert(
            title = todo.title,
            description = todo.description,
            done = todo.done,
            deadline = todo.deadline,
            userId = todo.userId,
        )
    }
}