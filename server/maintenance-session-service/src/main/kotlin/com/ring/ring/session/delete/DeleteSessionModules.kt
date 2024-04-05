package com.ring.ring.session.delete

import com.ring.ring.session.shared.SharedModules

internal object DeleteSessionModules {
    val deleteSessionRepository = createDeleteSessionRepository()

    private fun createDeleteSessionRepository(): DeleteSessionByUserIdRepository = DeleteSessionByUserIdRepository(
        remoteDataSource = createDeleteSessionRemoteDataSource(),
        localDataSource = createDeleteSessionDataSource(),
    )

    private fun createDeleteSessionRemoteDataSource() = DeleteSessionByUserIdRemoteDataSource(
        httpClient = SharedModules.httpClient,
    )

    private fun createDeleteSessionDataSource(): DeleteSessionByUserIdDataSource = DeleteSessionByUserIdDataSource(
        queries = SharedModules.db.sessionQueries
    )
}