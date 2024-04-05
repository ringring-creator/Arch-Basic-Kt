package com.ring.ring.session.logout

import com.ring.ring.session.shared.SharedModules

internal object LogoutModules {
    val validateSessionRepository = createValidateSessionRepository()
    val deleteSessionRepository = createDeleteSessionRepository()

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        httpClient = SharedModules.httpClient
    )

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        remoteDataSource = createDeleteSessionRemoteDataSource(),
        localDataSource = createDeleteSessionDataSource(),
    )

    private fun createDeleteSessionRemoteDataSource() = DeleteSessionRemoteDataSource(
        httpClient = SharedModules.httpClient,
    )

    private fun createDeleteSessionDataSource(): DeleteSessionDataSource = DeleteSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}