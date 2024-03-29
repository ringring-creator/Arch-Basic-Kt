package com.ring.ring.data.todo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
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
}