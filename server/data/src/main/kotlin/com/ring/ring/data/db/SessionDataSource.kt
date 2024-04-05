package com.ring.ring.data.db

import data.db.SessionQueries

class SessionDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )

    fun save(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )

    fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()

}