package com.ring.ring.session.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class ExistUserRemoteDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class ExistUserRequest(
        val user: User,
    )

    @Serializable
    data class ExistUserResponse(
        val userId: Long?
    )


    suspend fun exist(user: User): ExistUserResponse {
        return httpClient.post("$URL/user/exist") {
            contentType(ContentType.Application.Json)
            setBody(ExistUserRequest(user))
        }.body()
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}