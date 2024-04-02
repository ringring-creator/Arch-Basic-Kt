package com.ring.ring.todo.shared

internal class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}