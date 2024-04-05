package com.ring.ring.validateSession.validate

import com.ring.ring.validateSession.shared.SharedModules

internal object ValidateSessionModules {
    private val remoteSessionDataSource = createRemoteValidateSessionDataSource()
    private val validateSessionDataSource = createLocalValidateSessionDataSource()
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository() = ValidateSessionRepository(
        remoteSessionDataSource = remoteSessionDataSource,
        localDataSource = validateSessionDataSource
    )

    private fun createRemoteValidateSessionDataSource() = RemoteSessionDataSource(
        httpClient = SharedModules.httpClient,
    )

    private fun createLocalValidateSessionDataSource() = ValidateSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}