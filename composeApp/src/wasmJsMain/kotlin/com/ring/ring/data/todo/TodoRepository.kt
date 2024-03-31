package com.ring.ring.data.todo

import com.ring.ring.data.user.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Long?,
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: LocalDate,
    val userId: Long,
)

class TodoRepository(
    private val remoteDataSource: RemoteTodoDataSource
) {
    suspend fun create(todo: Todo) = withContext(Dispatchers.Default) {
        remoteDataSource.create(todo)
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