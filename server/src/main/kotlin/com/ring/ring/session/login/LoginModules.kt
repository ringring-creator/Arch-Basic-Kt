package com.ring.ring.session.login

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import com.ring.ring.user.get.GetUserDataSource
import com.ring.ring.user.get.GetUserRepository
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object LoginModules {
    val db = createDb()
    val getUserRepository = createGetUserRepository()
    val saveSessionRepository = createSaveSessionRepository()
    val insertSessionDataSource = createInsertSessionDataSource()

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): GetUserDataSource = GetUserDataSource(
        queries = db.userQueries
    )

    private fun createSaveSessionRepository(): SaveSessionRepository = SaveSessionRepository(
        dataSource = createInsertSessionDataSource()
    )

    private fun createInsertSessionDataSource(): InsertSessionDataSource = InsertSessionDataSource(
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