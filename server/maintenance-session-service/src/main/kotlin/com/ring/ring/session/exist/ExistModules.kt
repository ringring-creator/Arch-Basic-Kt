package com.ring.ring.session.exist

import com.ring.ring.session.shared.SharedModules

internal object ExistModules {
    val existSessionRepository = createExistSessionRepository()

    private fun createExistSessionRepository(): ExistSessionRepository = ExistSessionRepository(
        queries = SharedModules.db.sessionQueries
    )
}