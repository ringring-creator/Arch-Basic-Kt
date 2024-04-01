package com.ring.ring.todo.list

import com.ring.ring.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListTodoRepository(
    private val dataSource: ListTodoDataSource,
) {
    suspend fun list(userId: Long): List<Todo> = withContext(Dispatchers.IO) {
        dataSource.list(userId)
    }
}