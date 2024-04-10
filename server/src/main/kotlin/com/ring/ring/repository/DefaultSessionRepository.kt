package com.ring.ring.repository

import com.ring.ring.db.SessionDataSource
import com.ring.ring.di.DataModules
import java.security.SecureRandom
import java.util.*

interface SessionRepository {
    fun save(userId: Long): Session
    fun delete(session: Session)
    fun validate(session: Session): Boolean
}

class DefaultSessionRepository(
    private val dataSource: SessionDataSource = DataModules.sessionDataSource
) : SessionRepository {
    override fun save(userId: Long): Session {
        val session = Session(
            userId = userId,
            credential = generateSecureRandomString(),
        )
        dataSource.insert(session)
        return session
    }

    override fun delete(session: Session) = dataSource.delete(session)

    override fun validate(session: Session): Boolean = dataSource.validate(session)

    private fun generateSecureRandomString(): String {
        val secureRandom = SecureRandom()
        val randomBytes = ByteArray(CREDENTIAL_BYTE_LENGTH)
        secureRandom.nextBytes(randomBytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes)
    }

    companion object {
        private const val CREDENTIAL_BYTE_LENGTH = 128
    }
}