package com.ring.ring.todo.shared

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object ValidateSessionModules {
    val validateSessionRepository = createValidateSessionRepository()
    val validateSessionDataSource = createValidateSessionDataSource()

    private fun createValidateSessionRepository(): ValidateSessionRepository = ValidateSessionRepository(
        dataSource = createValidateSessionDataSource()
    )

    private fun createValidateSessionDataSource(): ValidateSessionRemoteDataSource = ValidateSessionRemoteDataSource(
        httpClient = createHttpClient(),
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