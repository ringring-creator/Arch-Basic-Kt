package com.ring.ring.data.todo

import com.ring.ring.data.user.RemoteUserDataSource
import com.ring.ring.data.user.Session
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class RemoteTodoDataSource(
    private val httpClient: HttpClient
) {
    suspend fun create(todo: Todo): Unit {
        httpClient.post("${RemoteUserDataSource.URL}/todo/create") {
            contentType(ContentType.Application.Json)
            setBody(todo)
        }
    }

    suspend fun list(session: Session): List<Todo> {
        return httpClient.post("${RemoteUserDataSource.URL}/todo/list") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body()
    }

    @Serializable
    data class GetRequest(
        val todoId: Long,
        val session: Session
    )

    suspend fun get(todoId: Long, session: Session): Todo {
        return httpClient.post("${RemoteUserDataSource.URL}/todo/get") {
            contentType(ContentType.Application.Json)
            setBody(GetRequest(todoId, session))
        }.body()
    }

    @Serializable
    data class EditRequest(
        val todo: Todo,
        val session: Session
    )

    suspend fun edit(todo: Todo, session: Session) {
        return httpClient.post("${RemoteUserDataSource.URL}/todo/edit") {
            contentType(ContentType.Application.Json)
            setBody(EditRequest(todo, session))
        }.body()
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}