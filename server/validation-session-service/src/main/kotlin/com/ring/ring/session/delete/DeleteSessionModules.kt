package com.ring.ring.session.delete

import com.ring.ring.session.shared.SharedModules

internal object DeleteSessionModules {
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository() = DeleteSessionRepository(
        localDataSource = createLocalValidateSessionDataSource()
    )

    private fun createLocalValidateSessionDataSource() = DeleteSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}