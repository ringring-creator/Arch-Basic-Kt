package com.ring.ring.session.delete

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class DeleteSessionRemoteDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class Request(
        val userId: Long,
    )

    suspend fun delete(userId: Long) {
        httpClient.post("$URL/session/delete") {
            contentType(ContentType.Application.Json)
            setBody(Request(userId))
        }
    }

    companion object {
        const val URL = "http://localhost:8084"
    }
}