package com.ring.ring.todo.edit

import com.ring.ring.todo.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class EditTodoRepository(
    private val dataSource: EditTodoDataSource,
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.update(todo)
    }

    suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        dataSource.updateDone(id, done)
    }
}