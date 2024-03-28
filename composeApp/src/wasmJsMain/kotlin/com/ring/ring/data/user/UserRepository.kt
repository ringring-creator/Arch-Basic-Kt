package com.ring.ring.data.user

import com.ring.ring.usecase.user.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
)

@Serializable
data class Session(
    val userId: Long,
    val credential: String,
) {
    fun toLogin(): Login.Res.Session = Login.Res.Session(userId, credential)
}

class UserRepository(
    private val remoteDataSource: RemoteUserDataSource
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        remoteDataSource.signUp(user)
    }

    suspend fun login(user: User): Session = withContext(Dispatchers.Default) {
        return@withContext remoteDataSource.login(user)
    }
}