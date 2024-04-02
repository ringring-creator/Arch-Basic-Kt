package com.ring.ring.todo.get

import com.ring.ring.com.ring.ring.todo.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTodoRepository(
    private val dataSource: GetTodoDataSource,
) {
    suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }
}