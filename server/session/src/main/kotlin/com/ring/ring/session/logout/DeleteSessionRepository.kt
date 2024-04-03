package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session

internal class DeleteSessionRepository(
    private val dataSource: DeleteSessionDataSource = LogoutModules.deleteSessionDataSource
) {
    fun delete(session: Session) = dataSource.delete(session)
}