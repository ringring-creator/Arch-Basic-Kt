package com.ring.ring.data.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
)

class UserRepository(
    private val remoteDataSource: RemoteUserDataSource
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        remoteDataSource.signUp(user)
    }
}