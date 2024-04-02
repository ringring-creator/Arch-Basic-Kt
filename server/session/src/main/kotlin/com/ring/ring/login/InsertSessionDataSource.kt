package com.ring.ring.login

import com.ring.ring.com.ring.ring.Session
import com.ring.ring.data.db.DataModules
import data.db.SessionQueries

class InsertSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun insert(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )
}