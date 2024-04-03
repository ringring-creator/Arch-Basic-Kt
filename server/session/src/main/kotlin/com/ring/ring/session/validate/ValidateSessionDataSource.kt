package com.ring.ring.session.validate

import com.ring.ring.data.db.DataModules
import com.ring.ring.session.shared.Session
import data.db.SessionQueries

internal class ValidateSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}