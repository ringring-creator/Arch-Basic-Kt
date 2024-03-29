package com.ring.ring.data

import app.cash.sqldelight.ColumnAdapter
import com.ring.ring.data.db.TodoDataSource
import com.ring.ring.usecase.todo.GetTodo
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Long?,
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: LocalDate,
    val userId: Long,
) {
    fun toGetTodoListItem(): GetTodoList.Res.Todo? = id?.let {
        GetTodoList.Res.Todo(
            id = it,
            title = title,
            description = description,
            done = done,
            deadline = deadline,
            userId = userId,
        )
    }

    fun toGetTodo(): GetTodo.Res.Todo = GetTodo.Res.Todo(
        id = id!!.toString(),
        title = title,
        description = description,
        done = done.toString(),
        deadline = deadline.toString(),
        userId = userId.toString(),
    )
}

class DeadlineAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return databaseValue.toLocalDate()
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}

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
        try {
            dataSource.upsert(todo)
        } catch (e: Exception) {
            println(e)
        }
    }

    suspend fun updateDone(id: Long, done: Boolean) = withContext(Dispatchers.IO) {
        dataSource.updateDone(id, done)
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}