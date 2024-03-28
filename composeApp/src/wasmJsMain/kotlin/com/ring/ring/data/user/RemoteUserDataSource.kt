package com.ring.ring.data.user

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteUserDataSource(
    private val httpClient: HttpClient
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        try {
            val response = httpClient.post("http://localhost:8081/user/signup") {
                headers {
                    append(HttpHeaders.AccessControlAllowOrigin, "http://localhost:8081")
                }
                contentType(ContentType.Application.Json)
                setBody(user)
            }
            println("response:$response")
        } catch (e: Throwable) {
            println("e:$e")
        }
    }
}