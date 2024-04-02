package com.ring.ring.user.shared

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    suspend fun validate(session: Session): Boolean = dataSource.validate(session).isValid
}