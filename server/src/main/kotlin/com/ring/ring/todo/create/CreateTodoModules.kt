package com.ring.ring.todo.create

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object CreateTodoModules {
    val db = createDb()
    val createTodoRepository = createCreateTodoRepository()

    private fun createCreateTodoRepository(): CreateTodoRepository = CreateTodoRepository(
        dataSource = createCreateTodoDataSource()
    )

    private fun createCreateTodoDataSource(): CreateTodoDataSource = CreateTodoDataSource(
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