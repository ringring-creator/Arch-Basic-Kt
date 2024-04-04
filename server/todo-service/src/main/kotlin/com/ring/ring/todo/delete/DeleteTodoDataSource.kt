package com.ring.ring.todo.delete

import todo.shared.TodoQueries

internal class DeleteTodoDataSource(private val queries: TodoQueries) {
    fun delete(id: Long) = queries.delete(id)
    fun deleteByUserId(userId: Long) {
        queries.deleteByUserId(userId)
    }
}