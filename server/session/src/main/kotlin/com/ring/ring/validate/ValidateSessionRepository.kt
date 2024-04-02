package com.ring.ring.validate

import com.ring.ring.com.ring.ring.Session
import com.ring.ring.com.ring.ring.validate.ValidateSessionModules

class ValidateSessionRepository(
    private val dataSource: ValidateSessionDataSource = ValidateSessionModules.validateSessionDataSource
) {
    fun validate(session: Session): Boolean = dataSource.validate(session)
}