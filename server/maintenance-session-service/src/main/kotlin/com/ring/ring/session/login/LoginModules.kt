package com.ring.ring.session.login

import com.ring.ring.session.shared.SharedModules
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object LoginModules {
    val getUserRepository = createGetUserRepository()
    val saveSessionRepository = createSaveSessionRepository()

    private fun createGetUserRepository(): ExistUserRepository = ExistUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): ExistUserRemoteDataSource = ExistUserRemoteDataSource(
        httpClient = createHttpClient()
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

    private fun createSaveSessionRepository(): SaveSessionRepository = SaveSessionRepository(
        dataSource = createInsertSessionDataSource()
    )

    private fun createInsertSessionDataSource(): SaveSessionDataSource = SaveSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}