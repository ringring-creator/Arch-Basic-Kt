package com.ring.ring.com.ring.ring.validate

import com.ring.ring.data.db.DataModules
import com.ring.ring.validate.ValidateSessionDataSource
import com.ring.ring.validate.ValidateSessionRepository

object ValidateSessionModules {
    val validateSessionRepository = createValidateSessionRepository()
    val validateSessionDataSource = createValidateSessionDataSource()

    private fun createValidateSessionRepository(): ValidateSessionRepository = ValidateSessionRepository(
        dataSource = createValidateSessionDataSource()
    )

    private fun createValidateSessionDataSource(): ValidateSessionDataSource = ValidateSessionDataSource(
        queries = DataModules.db.sessionQueries
    )
}