package com.ring.ring.session.login

import com.ring.ring.data.db.DataModules
import com.ring.ring.session.Session
import data.db.SessionQueries

class SaveSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun save(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )
}