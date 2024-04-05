package com.ring.ring.user.shared

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class ValidateSessionRepository(
    private val httpClient: HttpClient,
) {
    suspend fun validate(session: Session): Boolean {
        return httpClient.post("$URL/session/validate") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body<ValidateSessionResponse>().isValid
    }

    @Serializable
    private data class ValidateSessionResponse(
        val isValid: Boolean
    )

    companion object {
        const val URL = "http://localhost:8084"
    }
}