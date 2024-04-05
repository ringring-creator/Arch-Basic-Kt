package com.ring.ring.todo.shared

import com.ring.ring.data.db.Session
import com.ring.ring.data.db.SessionDataSource

internal class ValidateSessionRepository(
    private val dataSource: SessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}