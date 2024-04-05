package com.ring.ring.session.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import session.shared.LocalDb

object SharedModules {
    val db = createDb()
    val httpClient = createHttpClient()

    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/session.db",
        schema = LocalDb.Schema,
    )

    private fun createHttpClient() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}