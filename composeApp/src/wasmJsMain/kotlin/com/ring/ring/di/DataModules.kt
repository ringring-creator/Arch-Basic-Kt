package com.ring.ring.di

import com.ring.ring.data.user.RemoteUserDataSource
import com.ring.ring.data.user.UserRepository
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object DataModules {
    val userRepository = createUserRepository()

    private fun createUserRepository(): UserRepository {
        return UserRepository(
            remoteDataSource = createRemoteUserDataSource()
        )
    }

    private fun createRemoteUserDataSource(): RemoteUserDataSource {
        return RemoteUserDataSource(
            httpClient = createHttpClient()
        )
    }

    private fun createHttpClient(): HttpClient {
        return HttpClient(JsClient()) {
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
}