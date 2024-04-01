package com.ring.ring.data.db

import app.cash.sqldelight.ColumnAdapter
import com.ring.ring.data.Todo
import data.db.TodoQueries
import data.db.TodoTable
import kotlinx.datetime.LocalDate

class DeadlineAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return LocalDate.parse(databaseValue)
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}

class TodoDataSource(private val queries: TodoQueries) {
    fun list(userId: Long): List<Todo> = queries
        .selectAll(userId)
        .executeAsList()
        .map { convert(it) }

    fun get(id: Long): Todo = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    fun upsert(todo: Todo) {
        if (todo.id == null) {
            insert(todo = todo)
        } else {
            update(todo = todo)
        }
    }

    fun updateDone(id: Long, done: Boolean) = queries.updateDone(
        done = done,
        id = id,
    )

    fun delete(id: Long) = queries.delete(id)

    private fun insert(todo: Todo) = queries.insert(
        title = todo.title,
        description = todo.description,
        done = todo.done,
        deadline = todo.deadline,
        userId = todo.userId,
    )

    private fun update(todo: Todo) {
        val id = todo.id ?: return
        queries.update(
            id = id,
            title = todo.title,
            description = todo.description,
            done = todo.done,
            deadline = todo.deadline,
            userId = todo.userId,
        )
    }

    private fun convert(todoTable: TodoTable) = Todo(
        id = todoTable.id,
        title = todoTable.title,
        description = todoTable.description,
        done = todoTable.done,
        deadline = todoTable.deadline,
        userId = todoTable.userId,
    )
}