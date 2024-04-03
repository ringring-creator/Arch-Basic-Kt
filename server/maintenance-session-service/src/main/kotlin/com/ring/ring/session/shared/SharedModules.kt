package com.ring.ring.session.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import session.shared.LocalDb

object SharedModules {
    val db = createDb()
    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )


    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/session.db",
        schema = LocalDb.Schema,
    )
}