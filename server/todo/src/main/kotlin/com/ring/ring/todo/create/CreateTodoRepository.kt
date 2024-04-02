package com.ring.ring.todo.create

import com.ring.ring.todo.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateTodoRepository(
    private val dataSource: CreateTodoDataSource,
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.insert(todo)
    }
}