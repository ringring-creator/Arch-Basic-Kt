package com.ring.ring.data.remote

import com.ring.ring.data.Session
import com.ring.ring.data.Todo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class RemoteTodoDataSource(
    private val httpClient: HttpClient
) {
    @Serializable
    private data class CreateRequest(
        val todo: Todo,
        val session: Session
    )

    suspend fun create(todo: Todo, session: Session) {
        httpClient.post("${URL}/todo/create") {
            contentType(ContentType.Application.Json)
            setBody(CreateRequest(todo, session))
        }
    }

    @Serializable
    private data class ListRequest(
        val session: Session
    )

    suspend fun list(session: Session): List<Todo> {
        return httpClient.post("${URL}/todo/list") {
            contentType(ContentType.Application.Json)
            setBody(ListRequest(session))
        }.body()
    }

    @Serializable
    private data class GetRequest(
        val todoId: Long,
        val session: Session
    )

    suspend fun get(todoId: Long, session: Session): Todo {
        return httpClient.post("${URL}/todo/get") {
            contentType(ContentType.Application.Json)
            setBody(GetRequest(todoId, session))
        }.body()
    }

    @Serializable
    private data class EditRequest(
        val todo: Todo,
        val session: Session
    )

    suspend fun edit(todo: Todo, session: Session) {
        return httpClient.post("${URL}/todo/edit") {
            contentType(ContentType.Application.Json)
            setBody(EditRequest(todo, session))
        }.body()
    }

    @Serializable
    private data class DeleteRequest(
        val todoId: Long,
        val session: Session
    )

    suspend fun delete(todoId: Long, session: Session) {
        httpClient.post("${URL}/todo/delete") {
            contentType(ContentType.Application.Json)
            setBody(DeleteRequest(todoId, session))
        }
    }

    @Serializable
    private data class EditDoneRequest(
        val todoId: Long,
        val done: Boolean,
        val session: Session
    )

    suspend fun editDone(id: Long, done: Boolean, session: Session) {
        httpClient.post("${URL}/todo/editDone") {
            contentType(ContentType.Application.Json)
            setBody(EditDoneRequest(id, done, session))
        }
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}
