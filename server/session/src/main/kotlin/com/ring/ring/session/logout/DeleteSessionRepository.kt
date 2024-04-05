package com.ring.ring.session.logout

import com.ring.ring.data.db.DataModules
import com.ring.ring.data.db.Session
import com.ring.ring.data.db.SessionDataSource

internal class DeleteSessionRepository(
    private val dataSource: SessionDataSource = DataModules.sessionDataSource,
) {
    fun delete(session: Session) = dataSource.delete(session)
}