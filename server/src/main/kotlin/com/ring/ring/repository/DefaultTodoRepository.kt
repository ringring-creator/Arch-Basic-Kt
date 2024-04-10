package com.ring.ring.repository

import com.ring.ring.db.TodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TodoRepository {
    suspend fun list(userId: Long): List<Todo>
    suspend fun get(id: Long): Todo
    suspend fun save(todo: Todo)
    suspend fun updateDone(id: Long, done: Boolean)
    suspend fun delete(id: Long)
}

class DefaultTodoRepository(
    private val dataSource: TodoDataSource,
) : TodoRepository {
    override suspend fun list(userId: Long): List<Todo> = withContext(Dispatchers.IO) {
        dataSource.list(userId)
    }

    override suspend fun get(id: Long): Todo = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    override suspend fun save(todo: Todo) = withContext(Dispatchers.IO) {
        dataSource.upsert(todo)
    }

    override suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        dataSource.updateDone(id, done)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}