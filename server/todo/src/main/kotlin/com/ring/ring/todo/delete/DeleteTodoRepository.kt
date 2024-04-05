package com.ring.ring.todo.delete

import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DeleteTodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}