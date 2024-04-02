package com.ring.ring.todo.shared

import com.ring.ring.data.db.DataModules

internal object ValidateSessionModules {
    val validateSessionRepository = createValidateSessionRepository()
    val validateSessionDataSource = createValidateSessionDataSource()

    private fun createValidateSessionRepository(): ValidateSessionRepository = ValidateSessionRepository(
        dataSource = createValidateSessionDataSource()
    )

    private fun createValidateSessionDataSource(): ValidateSessionDataSource = ValidateSessionDataSource(
        queries = DataModules.db.sessionQueries
    )
}