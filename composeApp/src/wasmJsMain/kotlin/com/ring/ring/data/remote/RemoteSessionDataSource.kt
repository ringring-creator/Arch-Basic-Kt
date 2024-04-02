package com.ring.ring.data.remote

import com.ring.ring.data.Session
import com.ring.ring.data.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class RemoteSessionDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class LoginRequest(
        val user: User
    )

    suspend fun login(user: User): Session {
        return httpClient.post("$URL/session/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(user))
        }.body<Session>()
    }

    @Serializable
    private data class LogoutRequest(
        val session: Session
    )

    suspend fun logout(session: Session) {
        httpClient.post("$URL/session/logout") {
            contentType(ContentType.Application.Json)
            setBody(LogoutRequest(session))
        }
    }

    companion object {
        const val URL = "http://localhost:8082"
    }
}