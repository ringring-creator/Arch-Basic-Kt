package com.ring.ring.session.validate

import com.ring.ring.session.shared.Session
import com.ring.ring.session.shared.SharedModules
import session.shared.SessionQueries

internal class ValidateSessionDataSource(
    private val queries: SessionQueries = SharedModules.db.sessionQueries
) {
    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()

    fun save(session: Session) {
        queries.insert(session.userId, session.credential)
    }
}