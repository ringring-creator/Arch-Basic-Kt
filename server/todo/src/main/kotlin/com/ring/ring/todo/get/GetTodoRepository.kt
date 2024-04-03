package com.ring.ring.todo.get

import com.ring.ring.todo.shared.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetTodoRepository(
    private val dataSource: GetTodoDataSource,
) {
    suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }
}