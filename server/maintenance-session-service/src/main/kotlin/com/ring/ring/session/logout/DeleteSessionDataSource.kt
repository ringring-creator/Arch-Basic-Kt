package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.SharedModules
import session.shared.SessionQueries

internal class DeleteSessionDataSource(
    private val queries: SessionQueries = SharedModules.db.sessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )
}