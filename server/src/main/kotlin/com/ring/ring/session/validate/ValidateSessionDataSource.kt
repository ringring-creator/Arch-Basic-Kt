package com.ring.ring.session.validate

import com.ring.ring.session.Session
import data.db.SessionQueries

class ValidateSessionDataSource(
    private val queries: SessionQueries = ValidateSessionModules.db.sessionQueries
) {
    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}