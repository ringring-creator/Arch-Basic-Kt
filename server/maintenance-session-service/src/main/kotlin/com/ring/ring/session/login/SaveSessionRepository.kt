package com.ring.ring.session.login

import com.ring.ring.session.shared.Session
import java.security.SecureRandom
import java.util.*

internal class SaveSessionRepository(
    private val dataSource: SaveSessionDataSource
) {
    fun save(userId: Long): Session {
        val session = Session(
            userId = userId,
            credential = generateSecureRandomString(),
        )
        dataSource.save(session)
        return session
    }

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