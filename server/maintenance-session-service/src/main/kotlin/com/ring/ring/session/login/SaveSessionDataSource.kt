package com.ring.ring.session.login

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.SharedModules
import session.shared.SessionQueries

internal class SaveSessionDataSource(
    private val queries: SessionQueries = SharedModules.db.sessionQueries
) {
    fun save(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )
}