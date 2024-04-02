package com.ring.ring.com.ring.ring.todo

import com.ring.ring.data.db.DataModules
import com.ring.ring.todo.ValidateSessionDataSource
import com.ring.ring.todo.ValidateSessionRepository

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