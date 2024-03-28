package com.ring.ring.di

import com.ring.ring.data.KeyValueSessionDataSource
import com.ring.ring.data.session.SessionRepository
import com.ring.ring.data.user.RemoteUserDataSource
import com.ring.ring.data.user.UserRepository
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object DataModules {
    val userRepository = createUserRepository()
    val sessionRepository = createSessionRepository()

    private fun createUserRepository(): UserRepository = UserRepository(
        remoteDataSource = createRemoteUserDataSource()
    )

    private fun createRemoteUserDataSource(): RemoteUserDataSource = RemoteUserDataSource(
        httpClient = createHttpClient()
    )

    private fun createHttpClient(): HttpClient = HttpClient(JsClient()) {
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

    private fun createSessionRepository(): SessionRepository = SessionRepository(
        dataSource = createKeyValueSessionDataSource(),
    )

    private fun createKeyValueSessionDataSource(): KeyValueSessionDataSource = KeyValueSessionDataSource(
        settings = createSettings(),
    )

    private fun createSettings(): Settings = StorageSettings()
}