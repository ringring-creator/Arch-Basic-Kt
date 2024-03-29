package com.ring.ring.data.todo

import com.ring.ring.data.user.RemoteUserDataSource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteTodoDataSource(
    private val httpClient: HttpClient
) {
    suspend fun create(todo: Todo) = withContext(Dispatchers.Default) {
        httpClient.post("${RemoteUserDataSource.URL}/todo/create") {
            contentType(ContentType.Application.Json)
            setBody(todo)
        }
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}