package com.ring.ring.com.ring.ring.logout

import com.ring.ring.data.db.DataModules
import com.ring.ring.logout.DeleteSessionDataSource
import com.ring.ring.logout.DeleteSessionRepository

object LogoutModules {
    val deleteSessionRepository = createDeleteSessionRepository()
    val deleteSessionDataSource = createDeleteSessionDataSource()

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        dataSource = createDeleteSessionDataSource()
    )

    private fun createDeleteSessionDataSource(): DeleteSessionDataSource = DeleteSessionDataSource(
        queries = DataModules.db.sessionQueries
    )
}