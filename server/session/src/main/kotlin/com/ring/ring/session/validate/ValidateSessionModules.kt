package com.ring.ring.session.validate

import com.ring.ring.data.db.DataModules

internal object ValidateSessionModules {
    val validateSessionRepository = createValidateSessionRepository()

    private fun createValidateSessionRepository(): ValidateSessionRepository = ValidateSessionRepository(
        dataSource = DataModules.sessionDataSource
    )
}