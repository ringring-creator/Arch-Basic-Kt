package com.ring.ring.validateSession.delete

import com.ring.ring.validateSession.shared.Session
import com.ring.ring.validateSession.shared.SharedModules
import validateSession.shared.SessionQueries

internal class DeleteCacheDataSource(
    private val queries: SessionQueries = SharedModules.db.sessionQueries
) {
    fun delete(session: Session) {
        queries.delete(session.userId, session.credential)
    }

    fun deleteById(userId: Long) {
        queries.deleteById(userId)
    }
}