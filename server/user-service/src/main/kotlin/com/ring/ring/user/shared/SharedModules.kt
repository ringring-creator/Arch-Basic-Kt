package com.ring.ring.user.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import user.shared.LocalDb
import java.util.*

internal object SharedModules {
    val httpClient = createHttpClient()
    val validateSessionRepository = createValidateSessionRepository()
    val db = createDb()

    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/user.db",
        schema = LocalDb.Schema,
        properties = Properties().apply { put("foreign_keys", "true") }
    )

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        httpClient = httpClient,
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