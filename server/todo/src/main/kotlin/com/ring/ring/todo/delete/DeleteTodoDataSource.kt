package com.ring.ring.com.ring.ring.todo.delete

import data.db.TodoQueries

class DeleteTodoDataSource(private val queries: TodoQueries) {
    fun delete(id: Long) = queries.delete(id)
}