package com.ring.ring.data.repository

import com.ring.ring.data.Todo
import com.ring.ring.data.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val dataSource: TodoDataSource,
) {
    suspend fun list(userId: Long): List<Todo> = withContext(Dispatchers.IO) {
        dataSource.list(userId)
    }

    suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.upsert(todo)
    }

    suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        dataSource.updateDone(id, done)
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}