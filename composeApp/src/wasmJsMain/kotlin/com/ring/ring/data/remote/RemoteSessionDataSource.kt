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
    suspend fun login(user: User): Session {
        return httpClient.post("$URL/session/login") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body<Session>()
    }

    @Serializable
    data class LogoutRequest(
        val session: Session
    )

    suspend fun logout(session: Session) {
        httpClient.post("$URL/session/logout") {
            contentType(ContentType.Application.Json)
            setBody(LogoutRequest(session))
        }
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}