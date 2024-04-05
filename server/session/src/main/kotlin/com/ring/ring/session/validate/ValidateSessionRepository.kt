package com.ring.ring.session.validate

import com.ring.ring.data.db.DataModules
import com.ring.ring.data.db.Session
import com.ring.ring.data.db.SessionDataSource

internal class ValidateSessionRepository(
    private val dataSource: SessionDataSource = DataModules.sessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}