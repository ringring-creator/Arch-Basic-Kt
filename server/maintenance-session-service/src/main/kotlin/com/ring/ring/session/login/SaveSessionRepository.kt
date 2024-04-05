package com.ring.ring.session.login

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.SharedModules
import session.shared.SessionQueries
import java.security.SecureRandom
import java.util.*

internal class SaveSessionRepository(
    private val queries: SessionQueries = SharedModules.db.sessionQueries
) {
    fun save(userId: Long): Session {
        val session = Session(
            userId = userId,
            credential = generateSecureRandomString(),
        )
        save(session)
        return session
    }

    private fun save(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )

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