package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.Todo
import todo.shared.TodoQueries

internal class EditTodoDataSource(private val queries: TodoQueries) {
    fun updateDone(id: Long, done: Boolean) = queries.updateDone(
        done = done,
        id = id,
    )

    fun update(todo: Todo) {
        val id = todo.id ?: return
        queries.update(
            id = id,
            title = todo.title,
            description = todo.description,
            done = todo.done,
            deadline = todo.deadline,
            userId = todo.userId,
        )
    }
}