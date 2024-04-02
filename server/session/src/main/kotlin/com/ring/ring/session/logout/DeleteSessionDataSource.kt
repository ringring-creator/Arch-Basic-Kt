package com.ring.ring.session.logout

import com.ring.ring.data.db.DataModules
import com.ring.ring.session.Session
import data.db.SessionQueries

internal class DeleteSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )
}