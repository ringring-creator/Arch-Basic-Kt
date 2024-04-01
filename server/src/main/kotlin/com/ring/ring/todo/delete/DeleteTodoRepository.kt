package com.ring.ring.todo.delete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteTodoRepository(
    private val dataSource: DeleteTodoDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}