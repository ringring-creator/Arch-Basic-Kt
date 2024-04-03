package com.ring.ring.session.exist

import com.ring.ring.session.shared.SharedModules

internal object ExistModules {
    val validateSessionDataSource = createDeleteSessionDataSource()

    private fun createDeleteSessionDataSource(): ValidateSessionDataSource = ValidateSessionDataSource(
        queries = SharedModules.db.sessionQueries
    )
}