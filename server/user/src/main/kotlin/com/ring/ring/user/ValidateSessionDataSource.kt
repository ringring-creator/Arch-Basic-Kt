package com.ring.ring.user

import com.ring.ring.data.db.DataModules
import data.db.SessionQueries

class ValidateSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}