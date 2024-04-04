package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.Session

internal class DeleteCacheRepository(
    private val localDataSource: DeleteCacheDataSource
) {
    fun delete(session: Session) {
        localDataSource.delete(session)
    }

    fun deleteById(userId: Long) {
        localDataSource.deleteById(userId)
    }
}