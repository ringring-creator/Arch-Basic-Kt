package com.ring.ring.session.login

import com.ring.ring.session.Session
import data.db.SessionQueries

class InsertSessionDataSource(
    private val queries: SessionQueries = LoginModules.db.sessionQueries
) {
    fun insert(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )
}