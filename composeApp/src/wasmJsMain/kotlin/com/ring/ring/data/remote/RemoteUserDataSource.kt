package com.ring.ring.data.remote

import com.ring.ring.data.Session
import com.ring.ring.data.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

class RemoteUserDataSource(
    private val httpClient: HttpClient
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        httpClient.post("$URL/user/signup") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
    }

    @Serializable
    data class GetRequest(
        val session: Session,
    )

    suspend fun get(session: Session): User {
        return httpClient.post("$URL/user/get") {
            contentType(ContentType.Application.Json)
            setBody(GetRequest(session))
        }.body()
    }

    @Serializable
    data class EditRequest(
        val user: User,
        val session: Session,
    )

    suspend fun edit(user: User, session: Session) {
        httpClient.post("$URL/user/edit") {
            contentType(ContentType.Application.Json)
            setBody(EditRequest(user, session))
        }
    }

    @Serializable
    data class WithdrawalRequest(
        val session: Session
    )

    suspend fun withdrawal(session: Session) {
        httpClient.post("$URL/user/withdrawal") {
            contentType(ContentType.Application.Json)
            setBody(WithdrawalRequest(session))
        }
    }

    companion object {
        const val URL = "http://localhost:8081"
    }
}