package com.ring.ring.validateSession.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import validateSession.shared.LocalDb

object SharedModules {
    val httpClient = createHttpClient()
    val db = createDb()
    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = JdbcSqliteDriver.IN_MEMORY,
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