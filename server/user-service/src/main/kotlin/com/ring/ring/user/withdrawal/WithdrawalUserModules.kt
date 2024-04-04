package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.SharedModules
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object WithdrawalUserModules {
    val withdrawalUserRepository = createWithdrawalUserRepository()
    val todoRepository = createTodoRepository()
    val sessionRepository = createDeleteSessionRepository()

    private fun createDeleteSessionRepository() = DeleteSessionRepository(
        dataSource = createDeleteSessionDataSource(),
    )

    private fun createDeleteSessionDataSource() = DeleteSessionDataSource(
        httpClient = createHttpClient(),
    )

    private fun createTodoRepository() = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource() = DeleteTodoDataSource(
        httpClient = createHttpClient()
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

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): WithdrawalUserDataSource = WithdrawalUserDataSource(
        queries = SharedModules.db.userQueries
    )
}