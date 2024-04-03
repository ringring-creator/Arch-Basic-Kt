package com.ring.ring.todo.list

import com.ring.ring.todo.shared.Todo
import todo.shared.TodoQueries
import todo.shared.TodoTable

internal class ListTodoDataSource(private val queries: TodoQueries) {
    fun list(userId: Long): List<Todo> = queries
        .selectAll(userId)
        .executeAsList()
        .map { convert(it) }

    private fun convert(todoTable: TodoTable) = Todo(
        id = todoTable.id,
        title = todoTable.title,
        description = todoTable.description,
        done = todoTable.done,
        deadline = todoTable.deadline,
        userId = todoTable.userId,
    )
}