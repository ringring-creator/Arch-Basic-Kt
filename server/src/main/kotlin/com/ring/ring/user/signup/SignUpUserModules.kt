package com.ring.ring.user.signup

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object SignUpUserModules {
    val db = createDb()
    val signUpUserRepository = createSignUpUserRepository()
    val signUpUserDataSource = createSignUpUserDataSource()

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        dataSource = createSignUpUserDataSource()
    )

    private fun createSignUpUserDataSource(): SignUpUserDataSource = SignUpUserDataSource(
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