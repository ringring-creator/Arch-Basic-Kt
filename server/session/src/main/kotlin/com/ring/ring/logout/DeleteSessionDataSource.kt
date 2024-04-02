package com.ring.ring.logout

import com.ring.ring.com.ring.ring.Session
import com.ring.ring.data.db.DataModules
import data.db.SessionQueries

class DeleteSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )
}