package com.ring.ring.session.logout

import com.ring.ring.session.Session
import data.db.SessionQueries

class DeleteSessionDataSource(
    private val queries: SessionQueries = LogoutModules.db.sessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )
}