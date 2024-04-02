package com.ring.ring.todo

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: com.ring.ring.user.Session): Boolean = dataSource.validate(session)
}