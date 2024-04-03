package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

internal class ValidateSessionRemoteDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class ValidateSessionResponse(
        val isValid: Boolean
    )

    suspend fun validate(session: Session): Boolean {
        return httpClient.post("$URL/session/validate") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body<ValidateSessionResponse>().isValid
    }

    companion object {
        const val URL = "http://localhost:8084"
    }
}