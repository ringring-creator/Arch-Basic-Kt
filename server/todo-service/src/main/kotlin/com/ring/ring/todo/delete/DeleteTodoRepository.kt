package com.ring.ring.todo.delete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import todo.shared.TodoQueries

internal class DeleteTodoRepository(
    private val queries: TodoQueries,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        queries.delete(id)
    }

    fun deleteByUserId(userId: Long) {
        queries.deleteByUserId(userId)
    }
}