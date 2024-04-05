package com.ring.ring.todo.list

import com.ring.ring.todo.shared.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import todo.shared.TodoQueries
import todo.shared.TodoTable

internal class ListTodoRepository(
    private val queries: TodoQueries,
) {
    suspend fun list(userId: Long): List<Todo> = withContext(Dispatchers.IO) {
        return@withContext queries
            .selectAll(userId)
            .executeAsList()
            .map { convert(it) }
    }

    private fun convert(todoTable: TodoTable) = Todo(
        id = todoTable.id,
        title = todoTable.title,
        description = todoTable.description,
        done = todoTable.done,
        deadline = todoTable.deadline,
        userId = todoTable.userId,
    )
}