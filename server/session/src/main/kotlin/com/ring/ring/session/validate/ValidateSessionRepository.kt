package com.ring.ring.session.validate

import com.ring.ring.session.shared.Session

internal class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}