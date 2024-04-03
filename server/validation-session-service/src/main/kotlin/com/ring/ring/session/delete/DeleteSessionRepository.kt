package com.ring.ring.session.delete

import com.ring.ring.session.shared.Session

internal class DeleteSessionRepository(
    private val localDataSource: DeleteSessionDataSource
) {
    fun delete(session: Session) {
        localDataSource.delete(session)
    }
}