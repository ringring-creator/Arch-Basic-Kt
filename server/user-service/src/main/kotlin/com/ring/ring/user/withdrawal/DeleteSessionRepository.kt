package com.ring.ring.user.withdrawal

internal class DeleteSessionRepository(
    private val dataSource: DeleteSessionDataSource,
) {
    suspend fun delete(userId: Long) {
        dataSource.delete(userId)
    }
}