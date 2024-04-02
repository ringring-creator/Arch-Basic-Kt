package com.ring.ring.todo.get

import com.ring.ring.todo.Todo
import data.db.TodoQueries
import data.db.TodoTable

class GetTodoDataSource(private val queries: TodoQueries) {
    fun get(id: Long): Todo = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    private fun convert(todoTable: TodoTable) = Todo(
        id = todoTable.id,
        title = todoTable.title,
        description = todoTable.description,
        done = todoTable.done,
        deadline = todoTable.deadline,
        userId = todoTable.userId,
    )
}