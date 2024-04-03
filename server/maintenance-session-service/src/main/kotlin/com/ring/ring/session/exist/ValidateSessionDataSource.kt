package com.ring.ring.session.exist

import com.ring.ring.session.shared.Session
import session.shared.SessionQueries

internal class ValidateSessionDataSource(
    private val queries: SessionQueries
) {
    fun validate(session: Session) = queries.validate(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}