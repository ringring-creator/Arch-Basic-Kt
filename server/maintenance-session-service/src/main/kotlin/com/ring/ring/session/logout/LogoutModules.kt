package com.ring.ring.session.logout

import com.ring.ring.session.shared.SharedModules
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object LogoutModules {
    val validateSessionRepository = createValidateSessionRepository()
    val validateSessionDataSource = createValidateSessionRemoteDataSource()
    val deleteSessionRepository = createDeleteSessionRepository()
    val deleteSessionDataSource = createDeleteSessionDataSource()
    val deleteSessionRemoteDataSource = createDeleteSessionRemoteDataSource()

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        dataSource = createValidateSessionRemoteDataSource()
    )

    private fun createValidateSessionRemoteDataSource() = ValidateSessionRemoteDataSource(
        httpClient = createHttpClient()
    )

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        remoteDataSource = createDeleteSessionRemoteDataSource(),
        localDataSource = createDeleteSessionDataSource(),
    )

    private fun createDeleteSessionRemoteDataSource() = DeleteSessionRemoteDataSource(
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

    private fun createDeleteSessionDataSource(): DeleteSessionDataSource = DeleteSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}