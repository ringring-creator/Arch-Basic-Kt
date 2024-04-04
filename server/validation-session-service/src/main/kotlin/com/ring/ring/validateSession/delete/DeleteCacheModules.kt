package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.SharedModules

internal object DeleteCacheModules {
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository() = DeleteCacheRepository(
        localDataSource = createLocalValidateSessionDataSource()
    )

    private fun createLocalValidateSessionDataSource() = DeleteCacheDataSource(
        queries = SharedModules.db.sessionQueries
    )
}