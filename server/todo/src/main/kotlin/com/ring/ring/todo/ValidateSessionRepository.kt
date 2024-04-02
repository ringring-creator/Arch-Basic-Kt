package com.ring.ring.todo

import com.ring.ring.com.ring.ring.todo.ValidateSessionModules

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}