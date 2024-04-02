package com.ring.ring.todo.shared

import com.ring.ring.data.db.DataModules
import data.db.SessionQueries

internal class ValidateSessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}