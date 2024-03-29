package com.ring.ring.data.user

import com.ring.ring.usecase.user.GetUser
import com.ring.ring.usecase.user.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long?,
    val email: String,
    val password: String,
) {
    fun toGetUserElement(): GetUser.Res.User {
        return GetUser.Res.User(
            id = id!!,
            email = email,
            password = password,
        )
    }
}

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

    suspend fun get(session: Session): User = withContext(Dispatchers.Default) {
        return@withContext remoteDataSource.get(session)
    }
}