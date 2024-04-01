package com.ring.ring.session.validate

import com.ring.ring.di.DataModules
import com.ring.ring.session.Session

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = DataModules.validateSessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}