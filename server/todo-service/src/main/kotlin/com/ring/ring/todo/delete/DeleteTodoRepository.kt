package com.ring.ring.todo.delete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DeleteTodoRepository(
    private val dataSource: DeleteTodoDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }

    fun deleteByUserId(userId: Long) {
        dataSource.deleteByUserId(userId = userId)
    }
}