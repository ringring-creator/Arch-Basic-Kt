package com.ring.ring.repository

import com.ring.ring.data.Session
import com.ring.ring.data.User
import com.ring.ring.data.remote.RemoteUserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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