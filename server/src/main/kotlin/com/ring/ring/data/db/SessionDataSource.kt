package com.ring.ring.data.db

import com.ring.ring.data.Session
import com.ring.ring.di.DataModules
import data.db.SessionQueries

class SessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun insert(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )

    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )

    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}