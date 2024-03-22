package com.ring.ring.data

import com.ring.ring.data.db.SessionDataSource
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.user.Login
import java.security.SecureRandom
import java.util.*

data class Session(
    val userId: Long,
    val credential: String,
) {
    fun toLoginSession(): Login.Res.Session = Login.Res.Session(
        userId = userId,
        credential = credential,
    )
}

class SessionRepository(
    private val dataSource: SessionDataSource = DataModules.sessionDataSource
) {
    fun save(userId: Long): Session {
        val session = Session(
            userId = userId,
            credential = generateSecureRandomString(),
        )
        dataSource.insert(session)
        return session
    }

    fun delete(session: Session) = dataSource.delete(session)

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