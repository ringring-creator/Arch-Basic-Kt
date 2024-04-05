package com.ring.ring.todo.get

import com.ring.ring.data.db.Todo
import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetTodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }
}