package com.ring.ring.session.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
internal data class User(
    val email: String,
    val password: String,
)

internal class ExistUserRepository(
    private val httpClient: HttpClient
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        httpClient.post("$URL/user/exist") {
            contentType(ContentType.Application.Json)
            setBody(Request(user))
        }.body<Response>().userId
    }

    @Serializable
    private data class Request(
        val user: User,
    )

    @Serializable
    private data class Response(
        val userId: Long?
    )

    companion object {
        const val URL = "http://localhost:8081"
    }

}