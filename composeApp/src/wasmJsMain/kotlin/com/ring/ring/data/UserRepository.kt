package com.ring.ring.data

import com.ring.ring.data.remote.RemoteUserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long?,
    val email: String,
    val password: String,
)

@Serializable
data class Session(
    val userId: Long,
    val credential: String,
)

class UserRepository(
    private val remoteDataSource: RemoteUserDataSource
) {
    suspend fun signUp(user: User) = withContext(Dispatchers.Default) {
        remoteDataSource.signUp(user)
    }

    suspend fun get(session: Session): User = withContext(Dispatchers.Default) {
        return@withContext remoteDataSource.get(session)
    }

    suspend fun edit(user: User, session: Session) {
        remoteDataSource.edit(user, session)
    }

    suspend fun withdrawal(session: Session) {
        remoteDataSource.withdrawal(session)
    }
}