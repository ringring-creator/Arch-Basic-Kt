package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.SharedModules

internal object DeleteCacheModules {
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository() = DeleteCacheRepository(
        queries = SharedModules.db.sessionQueries,
    )
}