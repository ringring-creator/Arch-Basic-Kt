package com.ring.ring.user.shared

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class ValidateSessionDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    data class ValidateResponse(
        val isValid: Boolean
    )

    suspend fun validate(session: Session): ValidateResponse {
        return httpClient.post("$URL/session/validate") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body<ValidateResponse>()
    }

    companion object {
        const val URL = "http://localhost:8082"
    }

}