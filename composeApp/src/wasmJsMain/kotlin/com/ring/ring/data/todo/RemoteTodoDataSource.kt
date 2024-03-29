package com.ring.ring.data.todo

import com.ring.ring.data.user.RemoteUserDataSource
import com.ring.ring.data.user.Session
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

class RemoteTodoDataSource(
    private val httpClient: HttpClient
) {
    suspend fun create(todo: Todo): Unit = withContext(Dispatchers.Default) {
        httpClient.post("${RemoteUserDataSource.URL}/todo/create") {
            contentType(ContentType.Application.Json)
            setBody(todo)
        }
    }

    suspend fun list(session: Session): List<Todo> = withContext(Dispatchers.Default) {
        return@withContext httpClient.post("${RemoteUserDataSource.URL}/todo/list") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body()
    }

    @Serializable
    data class GetRequest(
        val todoId: Long,
        val session: Session
    )

    suspend fun get(todoId: Long, session: Session): Todo = withContext(Dispatchers.Default) {
        return@withContext httpClient.post("${RemoteUserDataSource.URL}/todo/get") {
            contentType(ContentType.Application.Json)
            setBody(GetRequest(todoId, session))
        }.body()
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}