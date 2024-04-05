package com.ring.ring.session.logout

import com.ring.ring.data.db.DataModules

internal object LogoutModules {
    val deleteSessionRepository = createDeleteSessionRepository()

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        dataSource = DataModules.sessionDataSource
    )

}