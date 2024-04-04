package com.ring.ring.session.delete

internal class DeleteSessionRepository(
    private val remoteDataSource: DeleteSessionRemoteDataSource,
    private val localDataSource: DeleteSessionDataSource,
) {
    suspend fun delete(userId: Long) {
        localDataSource.delete(userId)
        remoteDataSource.delete(userId)
    }
}