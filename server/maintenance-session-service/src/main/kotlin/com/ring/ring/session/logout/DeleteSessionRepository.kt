package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session

internal class DeleteSessionRepository(
    private val remoteDataSource: DeleteSessionRemoteDataSource = LogoutModules.deleteSessionRemoteDataSource,
    private val localDataSource: DeleteSessionDataSource = LogoutModules.deleteSessionDataSource,
) {
    suspend fun delete(session: Session) {
        localDataSource.delete(session)
        remoteDataSource.delete(session)
    }
}