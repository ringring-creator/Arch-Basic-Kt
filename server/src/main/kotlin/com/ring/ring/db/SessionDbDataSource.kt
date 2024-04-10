package com.ring.ring.db

import com.ring.ring.di.DataModules
import com.ring.ring.repository.Session
import db.SessionQueries

interface SessionDataSource {
    fun insert(session: Session)
    fun delete(session: Session)
    fun validate(session: Session): Boolean
}

class SessionDbDataSource(
    private val queries: SessionQueries = DataModules.db.sessionQueries
) : SessionDataSource {
    override fun insert(session: Session) = queries.insert(
        userId = session.userId,
        credential = session.credential,
    )

    override fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )

    override fun validate(session: Session) = queries.valid(
        userId = session.userId,
        credential = session.credential
    ).executeAsOne()
}