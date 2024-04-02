package com.ring.ring.todo.shared

import com.ring.ring.user.shared.Session
import com.ring.ring.user.shared.ValidateSessionDataSource

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    suspend fun validate(session: Session): Boolean = dataSource.validate(session).isValid
}