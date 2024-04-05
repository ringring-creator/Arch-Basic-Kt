package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import todo.shared.TodoQueries

internal class EditTodoRepository(
    private val queries: TodoQueries
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        val id = todo.id ?: return@withContext
        queries.update(
            id = id,
            title = todo.title,
            description = todo.description,
            done = todo.done,
            deadline = todo.deadline,
            userId = todo.userId,
        )
    }

    suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        queries.updateDone(
            done = done,
            id = id,
        )
    }
}