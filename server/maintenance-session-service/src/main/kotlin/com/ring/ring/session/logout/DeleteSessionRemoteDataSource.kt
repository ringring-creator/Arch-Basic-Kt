package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class DeleteSessionRemoteDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class Request(
        val session: Session,
    )

    suspend fun delete(session: Session) {
        httpClient.post("$URL/session/delete") {
            contentType(ContentType.Application.Json)
            setBody(Request(session))
        }
    }

    companion object {
        const val URL = "http://localhost:8084"
    }
}