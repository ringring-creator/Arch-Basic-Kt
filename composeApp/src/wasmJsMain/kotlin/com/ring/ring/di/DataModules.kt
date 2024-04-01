package com.ring.ring.di

import com.ring.ring.data.SessionRepository
import com.ring.ring.data.TodoRepository
import com.ring.ring.data.UserRepository
import com.ring.ring.data.local.KeyValueSessionDataSource
import com.ring.ring.data.remote.RemoteSessionDataSource
import com.ring.ring.data.remote.RemoteTodoDataSource
import com.ring.ring.data.remote.RemoteUserDataSource
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
    val todoRepository = createTodoRepository()

    private fun createUserRepository(): UserRepository = UserRepository(
        remoteDataSource = createRemoteUserDataSource()
    )

    private fun createRemoteUserDataSource(): RemoteUserDataSource = RemoteUserDataSource(
        httpClient = createHttpClient()
    )

    private fun createSessionRepository(): SessionRepository = SessionRepository(
        remoteDataSource = createRemoteSessionDataSource(),
        localDataSource = createKeyValueSessionDataSource(),
    )

    private fun createRemoteSessionDataSource(): RemoteSessionDataSource = RemoteSessionDataSource(
        httpClient = createHttpClient(),
    )

    private fun createTodoRepository(): TodoRepository = TodoRepository(
        remoteDataSource = createRemoteDataSource()
    )

    private fun createRemoteDataSource(): RemoteTodoDataSource = RemoteTodoDataSource(
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

    private fun createKeyValueSessionDataSource(): KeyValueSessionDataSource = KeyValueSessionDataSource(
        settings = createSettings(),
    )

    private fun createSettings(): Settings = StorageSettings()
}