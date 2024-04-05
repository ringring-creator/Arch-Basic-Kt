package com.ring.ring.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object DataModules {
    val db = createDb()
    val todoDataSource = createTodoDataSource()
    val sessionDataSource = createSessionDataSource()
    val userDataSource = createUserDataSource()

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

    private fun createTodoDataSource() = TodoDataSource(
        queries = db.todoQueries
    )

    private fun createSessionDataSource(): SessionDataSource = SessionDataSource(
        queries = db.sessionQueries
    )

    private fun createUserDataSource() = UserDataSource(
        queries = db.userQueries
    )
}