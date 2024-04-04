package com.ring.ring.validateSession.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import validateSession.shared.LocalDb

object SharedModules {
    val db = createDb()
    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = JdbcSqliteDriver.IN_MEMORY,
        schema = LocalDb.Schema,
    )
}