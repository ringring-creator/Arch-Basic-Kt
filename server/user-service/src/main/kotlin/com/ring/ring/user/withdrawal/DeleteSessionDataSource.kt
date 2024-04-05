package com.ring.ring.user.withdrawal

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class DeleteSessionDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    data class DeleteRequest(
        val userId: Long
    )

    suspend fun delete(userId: Long) {
        httpClient.post("$URL/session/delete") {
            contentType(ContentType.Application.Json)
            setBody(DeleteRequest(userId))
        }
    }

    companion object {
        const val URL = "http://localhost:8082"
    }
}