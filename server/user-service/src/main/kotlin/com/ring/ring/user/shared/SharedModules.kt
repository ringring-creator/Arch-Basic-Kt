package com.ring.ring.user.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.user.withdrawal.DeleteTaskRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import user.shared.DeleteTaskTable
import user.shared.LocalDb
import java.util.*

internal object SharedModules {
    val db = createDb()
    val httpClient = createHttpClient()
    val validateSessionRepository = createValidateSessionRepository()
    val deleteTaskRepository = createDeleteTaskRepository()

    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
        DeleteTaskTableAdapter = DeleteTaskTable.Adapter(
            typeAdapter = DeleteTaskTypeAdapter(),
        ),
    )

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/user.db",
        schema = LocalDb.Schema,
        properties = Properties().apply { put("foreign_keys", "true") }
    )

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        httpClient = httpClient,
    )

    private fun createDeleteTaskRepository() = DeleteTaskRepository(
        queries = db.deleteTaskQueries
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