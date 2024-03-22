package com.ring.ring.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.DeadlineAdapter
import com.ring.ring.data.SessionRepository
import com.ring.ring.data.TodoRepository
import com.ring.ring.data.UserRepository
import com.ring.ring.data.db.SessionDataSource
import com.ring.ring.data.db.TodoDataSource
import com.ring.ring.data.db.UserDataSource
import data.db.TodoTable
import data.local.db.LocalDb

object DataModules {
    val db = createDb()
    val todoRepository = createTodoRepository()
    val userRepository = createUserRepository()
    val userDataSource = createUserDataSource()
    val sessionRepository = createSessionRepository()
    val sessionDataSource = createSessionDataSource()

    private fun createTodoRepository(): TodoRepository = TodoRepository(
        dataSource = createTodoDataSource()
    )

    private fun createTodoDataSource(): TodoDataSource = TodoDataSource(
        queries = db.todoQueries
    )

    private fun createUserRepository(): UserRepository = UserRepository(
        dataSource = createUserDataSource()
    )

    private fun createUserDataSource(): UserDataSource = UserDataSource(
        queries = db.userQueries
    )

    private fun createSessionRepository(): SessionRepository = SessionRepository(
        dataSource = createSessionDataSource()
    )

    private fun createSessionDataSource(): SessionDataSource = SessionDataSource(
        queries = db.sessionQueries
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
    )

    private fun createInMemorySqliteDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        LocalDb.Schema.create(driver)
        return driver
    }
}