package com.ring.ring.db

import app.cash.sqldelight.ColumnAdapter
import com.ring.ring.repository.Todo
import db.TodoQueries
import db.TodoTable
import kotlinx.datetime.LocalDate

class DeadlineAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return LocalDate.parse(databaseValue)
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}

interface TodoDataSource {
    fun list(userId: Long): List<Todo>
    fun get(id: Long): Todo
    fun upsert(todo: Todo)
    fun updateDone(id: Long, done: Boolean)
    fun delete(id: Long)
}

class TodoDbDataSource(private val queries: TodoQueries) : TodoDataSource {
    override fun list(userId: Long): List<Todo> = queries
        .selectAll(userId)
        .executeAsList()
        .map { convert(it) }

    override fun get(id: Long): Todo = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    override fun upsert(todo: Todo) {
        if (todo.id == null) {
            insert(todo = todo)
        } else {
            update(todo = todo)
        }
    }

    override fun updateDone(id: Long, done: Boolean) = queries.updateDone(
        done = done,
        id = id,
    )

    override fun delete(id: Long) = queries.delete(id)

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