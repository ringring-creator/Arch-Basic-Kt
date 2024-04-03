package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session
import session.shared.SessionQueries

internal class DeleteSessionDataSource(
    private val queries: SessionQueries
) {
    fun delete(session: Session) = queries.delete(
        userId = session.userId,
        credential = session.credential
    )
}