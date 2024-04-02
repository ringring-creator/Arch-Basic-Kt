package com.ring.ring.todo.delete

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object DeleteTodoModules {
    val db = createDb()
    val deleteTodoRepository = createDeleteTodoRepository()

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource(): DeleteTodoDataSource = DeleteTodoDataSource(
        queries = db.todoQueries
    )

    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
        TodoTableAdapter = createTodoTableAdapter()
    )

    private fun createTodoTableAdapter() = TodoTable.Adapter(
        deadlineAdapter = createDeadlineAdapter()
    )

    private fun createDeadlineAdapter() = DeadlineAdapter()

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/database.db",
        schema = LocalDb.Schema,
        properties = Properties().apply { put("foreign_keys", "true") }
    )
}