package com.ring.ring.data.repository

import com.ring.ring.data.Session
import com.ring.ring.data.User
import com.ring.ring.data.local.KeyValueSessionDataSource
import com.ring.ring.data.remote.RemoteSessionDataSource

class SessionRepository(
    private val remoteDataSource: RemoteSessionDataSource,
    private val localDataSource: KeyValueSessionDataSource,
) {
    fun get(): Session? {
        return Session(
            userId = localDataSource.getLong(SESSION_USER_ID) ?: return null,
            credential = localDataSource.getString(SESSION_CREDENTIAL) ?: return null,
        )
    }

    fun delete() {
        localDataSource.remove(SESSION_USER_ID)
        localDataSource.remove(SESSION_CREDENTIAL)
    }

    fun save(session: Session) {
        localDataSource.save(SESSION_USER_ID, session.userId)
        localDataSource.save(SESSION_CREDENTIAL, session.credential)
    }

    suspend fun login(user: User): Session = remoteDataSource.login(user)

    suspend fun logout() {
        remoteDataSource.logout(
            session = get() ?: throw IllegalStateException()
        )
    }

    companion object {
        const val SESSION_USER_ID: String = "sessionUserId"
        const val SESSION_CREDENTIAL: String = "sessionCredential"
    }
}