package com.ring.ring.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.db.LocalDb
import java.util.*

object DataModules {
    val db = createDb()
    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/database.db",
        schema = LocalDb.Schema,
        properties = Properties().apply { put("foreign_keys", "true") }
    )
}