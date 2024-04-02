package com.ring.ring.todo.delete

import data.db.TodoQueries

internal class DeleteTodoDataSource(private val queries: TodoQueries) {
    fun delete(id: Long) = queries.delete(id)
}