package com.ring.ring.user

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: com.ring.ring.user.Session): Boolean = dataSource.validate(session)
}