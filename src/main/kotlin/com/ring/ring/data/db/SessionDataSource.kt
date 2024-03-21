package com.ring.ring.data.db

import com.ring.ring.data.Session
import com.ring.ring.di.DataModules
import data.db.SessionQueries
import data.db.SessionTable

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


    private fun convert(table: SessionTable) = Session(
        userId = table.userId,
        credential = table.credential,
    )
}