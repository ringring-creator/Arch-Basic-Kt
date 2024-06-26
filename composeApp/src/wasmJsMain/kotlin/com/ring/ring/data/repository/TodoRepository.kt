package com.ring.ring.data.repository

import com.ring.ring.data.Session
import com.ring.ring.data.Todo
import com.ring.ring.data.remote.RemoteTodoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val remoteDataSource: RemoteTodoDataSource
) {
    suspend fun create(todo: Todo, session: Session) = withContext(Dispatchers.Default) {
        remoteDataSource.create(todo, session)
    }

    suspend fun list(session: Session): List<Todo> = withContext(Dispatchers.Default) {
        return@withContext remoteDataSource.list(session)
    }

    suspend fun get(todoId: Long, session: Session): Todo = withContext(Dispatchers.Default) {
        return@withContext remoteDataSource.get(todoId, session)
    }

    suspend fun edit(todo: Todo, session: Session) = withContext(Dispatchers.Default) {
        remoteDataSource.edit(todo, session)
    }

    suspend fun delete(id: Long, session: Session) = withContext(Dispatchers.Default) {
        remoteDataSource.delete(id, session)
    }

    suspend fun editDone(id: Long, done: Boolean, session: Session) = withContext(Dispatchers.Default) {
        remoteDataSource.editDone(id, done, session)
    }
}