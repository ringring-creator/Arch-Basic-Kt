package com.ring.ring.todo.create

import com.ring.ring.data.db.Todo
import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CreateTodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.insert(todo)
    }
}