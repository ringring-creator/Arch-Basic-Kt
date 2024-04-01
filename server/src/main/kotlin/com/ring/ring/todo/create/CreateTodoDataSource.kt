package com.ring.ring.todo.create

import com.ring.ring.data.Todo
import data.db.TodoQueries

class CreateTodoDataSource(private val queries: TodoQueries) {
    fun insert(todo: Todo) = queries.insert(
        title = todo.title,
        description = todo.description,
        done = todo.done,
        deadline = todo.deadline,
        userId = todo.userId,
    )
}