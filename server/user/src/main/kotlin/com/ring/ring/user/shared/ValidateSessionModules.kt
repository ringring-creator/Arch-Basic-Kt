package com.ring.ring.user.shared

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ValidateSessionModules {
    val validateSessionDataSource = createValidateSessionDataSource()

    private fun createValidateSessionDataSource(): ValidateSessionDataSource = ValidateSessionDataSource(
        httpClient = createHttpClient(),
    )

    private fun createHttpClient(): HttpClient = HttpClient(CIO) {
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