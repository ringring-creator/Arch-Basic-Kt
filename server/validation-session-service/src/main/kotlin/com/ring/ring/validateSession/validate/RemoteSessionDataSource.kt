package com.ring.ring.validateSession.validate

import com.ring.ring.validateSession.shared.Session
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class RemoteSessionDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class ExistRequest(
        val session: Session
    )

    @Serializable
    private data class ExistResponse(
        val isValid: Boolean
    )

    suspend fun exist(session: Session): Boolean {
        return httpClient.post("$URL/session/exist") {
            contentType(ContentType.Application.Json)
            setBody(ExistRequest(session))
        }.body<ExistResponse>().isValid
    }

    companion object {
        const val URL = "http://localhost:8082"
    }
}