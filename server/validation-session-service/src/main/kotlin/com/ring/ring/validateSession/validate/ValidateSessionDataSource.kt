package com.ring.ring.validateSession.validate

import com.ring.ring.validateSession.shared.Session
import com.ring.ring.validateSession.shared.SharedModules
import validateSession.shared.SessionQueries

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