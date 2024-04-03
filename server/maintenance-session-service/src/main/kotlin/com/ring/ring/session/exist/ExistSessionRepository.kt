package com.ring.ring.session.exist

import com.ring.ring.session.shared.Session

internal class ExistSessionRepository(
    private val dataSource: ValidateSessionDataSource = ExistModules.validateSessionDataSource
) {
    fun validate(session: Session) = dataSource.validate(session)
}