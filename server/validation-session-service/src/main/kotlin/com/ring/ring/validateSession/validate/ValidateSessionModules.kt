package com.ring.ring.validateSession.validate

import com.ring.ring.validateSession.shared.SharedModules
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object ValidateSessionModules {
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        remoteSessionDataSource = createRemoteValidateSessionDataSource(),
        localDataSource = createLocalValidateSessionDataSource()
    )

    private fun createRemoteValidateSessionDataSource() = RemoteSessionDataSource(
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

    private fun createLocalValidateSessionDataSource() = ValidateSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}