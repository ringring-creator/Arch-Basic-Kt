package com.ring.ring.session.logout

import com.ring.ring.di.DataModules
import com.ring.ring.session.Session

class DeleteSessionRepository(
    private val dataSource: DeleteSessionDataSource = DataModules.deleteSessionDataSource
) {
    fun delete(session: Session) = dataSource.delete(session)
}