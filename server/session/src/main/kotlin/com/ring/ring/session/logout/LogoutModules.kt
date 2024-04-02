package com.ring.ring.session.logout

import com.ring.ring.data.db.DataModules

internal object LogoutModules {
    val deleteSessionRepository = createDeleteSessionRepository()
    val deleteSessionDataSource = createDeleteSessionDataSource()

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        dataSource = createDeleteSessionDataSource()
    )

    private fun createDeleteSessionDataSource(): DeleteSessionDataSource = DeleteSessionDataSource(
        queries = DataModules.db.sessionQueries
    )
}