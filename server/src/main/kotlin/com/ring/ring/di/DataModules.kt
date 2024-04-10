package com.ring.ring.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.db.DeadlineAdapter
import com.ring.ring.db.SessionDbDataSource
import com.ring.ring.db.TodoDbDataSource
import com.ring.ring.db.UserDbDataSource
import com.ring.ring.repository.*
import db.LocalDb
import db.TodoTable
import java.util.*

object DataModules {
    val db = createDb()
    val todoRepository: TodoRepository = createTodoRepository()
    val userRepository: UserRepository = createUserRepository()
    val sessionRepository: SessionRepository = createSessionRepository()
    val userDataSource = createUserDataSource()
    val sessionDataSource = createSessionDataSource()

    private fun createTodoRepository(): DefaultTodoRepository = DefaultTodoRepository(
        dataSource = createTodoDataSource()
    )

    private fun createTodoDataSource(): TodoDbDataSource = TodoDbDataSource(
        queries = db.todoQueries
    )

    private fun createUserRepository(): DefaultUserRepository = DefaultUserRepository(
        dataSource = createUserDataSource()
    )

    private fun createUserDataSource(): UserDbDataSource = UserDbDataSource(
        queries = db.userQueries
    )

    private fun createSessionRepository(): DefaultSessionRepository = DefaultSessionRepository(
        dataSource = createSessionDataSource()
    )

    private fun createSessionDataSource(): SessionDbDataSource = SessionDbDataSource(
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
        properties = Properties().apply { put("foreign_keys", "true") }
    )

    private fun createInMemorySqliteDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        LocalDb.Schema.create(driver)
        return driver
    }
}