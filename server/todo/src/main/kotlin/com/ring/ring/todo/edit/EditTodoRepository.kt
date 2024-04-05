package com.ring.ring.todo.edit

import com.ring.ring.data.db.Todo
import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class EditTodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.update(todo)
    }

    suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        dataSource.updateDone(id, done)
    }
}