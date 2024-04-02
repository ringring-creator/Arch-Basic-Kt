package com.ring.ring.user.edit

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object EditUserModules {
    val db = createDb()
    val editUserRepository = createEditUserRepository()
    val editUserDataSource = createEditUserDataSource()

    private fun createEditUserRepository(): EditUserRepository = EditUserRepository(
        dataSource = createEditUserDataSource()
    )

    private fun createEditUserDataSource(): EditUserDataSource = EditUserDataSource(
        queries = db.userQueries
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