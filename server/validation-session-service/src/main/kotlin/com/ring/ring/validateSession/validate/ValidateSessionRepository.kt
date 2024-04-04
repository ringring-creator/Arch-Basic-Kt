package com.ring.ring.validateSession.validate

import com.ring.ring.validateSession.shared.Session

internal class ValidateSessionRepository(
    private val remoteSessionDataSource: RemoteSessionDataSource,
    private val localDataSource: ValidateSessionDataSource
) {
    suspend fun validate(session: Session): Boolean {
        if (localDataSource.validate(session)) return true
        return remoteValidateAndCache(session)
    }

    private suspend fun remoteValidateAndCache(session: Session): Boolean {
        val isValid = remoteSessionDataSource.exist(session)
        if (isValid) {
            localDataSource.save(session)
        }
        return isValid
    }
}