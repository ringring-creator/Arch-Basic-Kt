package com.ring.ring.logout

import com.ring.ring.com.ring.ring.Session
import com.ring.ring.com.ring.ring.logout.LogoutModules

class DeleteSessionRepository(
    private val dataSource: DeleteSessionDataSource = LogoutModules.deleteSessionDataSource
) {
    fun delete(session: Session) = dataSource.delete(session)
}