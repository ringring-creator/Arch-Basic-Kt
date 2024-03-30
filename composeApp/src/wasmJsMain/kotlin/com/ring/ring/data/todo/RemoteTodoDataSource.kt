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
    suspend fun create(todo: Todo) {
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

    @Serializable
    data class DeleteRequest(
        val todoId: Long,
        val session: Session
    )

    suspend fun delete(todoId: Long, session: Session) {
        httpClient.post("${RemoteUserDataSource.URL}/todo/delete") {
            contentType(ContentType.Application.Json)
            setBody(DeleteRequest(todoId, session))
        }
    }

    @Serializable
    data class EditDoneRequest(
        val todoId: Long,
        val done: Boolean,
        val session: Session
    )

    suspend fun editDone(id: Long, done: Boolean, session: Session) {
        httpClient.post("${RemoteUserDataSource.URL}/todo/editDone") {
            contentType(ContentType.Application.Json)
            setBody(EditDoneRequest(id, done, session))
        }
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}
