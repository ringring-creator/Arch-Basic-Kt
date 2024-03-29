package com.ring.ring.data.user

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteUserDataSource(
    private val httpClient: HttpClient
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        httpClient.post("$URL/user/signup") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
    }

    suspend fun login(user: User): Session {
        return httpClient.post("$URL/user/login") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body<Session>()
    }

    suspend fun get(session: Session): User {
        return httpClient.post("$URL/user/get") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body()
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}