package com.ring.ring.user.shared

internal class ValidateSessionRepository(
    private val dataSource: ValidateSessionRemoteDataSource,
) {
    suspend fun validate(session: Session): Boolean = dataSource.validate(session)
}