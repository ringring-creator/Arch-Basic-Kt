package com.ring.ring.todo.list

import com.ring.ring.data.db.Todo
import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ListTodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun list(userId: Long): List<Todo> = withContext(Dispatchers.IO) {
        dataSource.list(userId)
    }
}