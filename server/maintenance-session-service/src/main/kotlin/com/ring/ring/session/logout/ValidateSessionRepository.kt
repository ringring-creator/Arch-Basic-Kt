package com.ring.ring.session.logout

import com.ring.ring.session.shared.Session

internal class ValidateSessionRepository(
    private val dataSource: ValidateSessionRemoteDataSource
) {
    suspend fun validate(session: Session): Boolean = dataSource.validate(session)
}