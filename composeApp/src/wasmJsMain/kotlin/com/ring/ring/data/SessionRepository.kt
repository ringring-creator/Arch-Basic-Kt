package com.ring.ring.data

import com.ring.ring.data.local.KeyValueSessionDataSource

class SessionRepository(
    private val dataSource: KeyValueSessionDataSource,
) {
    fun get(): Session? {
        return Session(
            userId = dataSource.getLong(SESSION_USER_ID) ?: return null,
            credential = dataSource.getString(SESSION_CREDENTIAL) ?: return null,
        )
    }

    fun delete() {
        dataSource.remove(SESSION_USER_ID)
        dataSource.remove(SESSION_CREDENTIAL)
    }

    fun save(session: Session) {
        dataSource.save(SESSION_USER_ID, session.userId)
        dataSource.save(SESSION_CREDENTIAL, session.credential)
    }

    companion object {
        const val SESSION_USER_ID: String = "sessionUserId"
        const val SESSION_CREDENTIAL: String = "sessionCredential"
    }
}