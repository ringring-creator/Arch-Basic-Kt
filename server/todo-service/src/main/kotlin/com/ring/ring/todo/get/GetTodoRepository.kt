package com.ring.ring.todo.get

import com.ring.ring.todo.shared.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import todo.shared.TodoQueries
import todo.shared.TodoTable

internal class GetTodoRepository(
    private val queries: TodoQueries
) {
    suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        return@withContext queries
            .selectById(id)
            .executeAsOne()
            .let { convert(it) }
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