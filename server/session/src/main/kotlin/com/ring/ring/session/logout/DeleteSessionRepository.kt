package com.ring.ring.session.logout

import com.ring.ring.session.Session

class DeleteSessionRepository(
    private val dataSource: DeleteSessionDataSource = LogoutModules.deleteSessionDataSource
) {
    fun delete(session: Session) = dataSource.delete(session)
}