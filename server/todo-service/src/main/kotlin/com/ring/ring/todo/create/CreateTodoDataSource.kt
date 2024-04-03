package com.ring.ring.todo.create

import com.ring.ring.todo.shared.Todo
import todo.shared.TodoQueries

internal class CreateTodoDataSource(private val queries: TodoQueries) {
    fun insert(todo: Todo) = queries.insert(
        title = todo.title,
        description = todo.description,
        done = todo.done,
        deadline = todo.deadline,
        userId = todo.userId,
    )
}