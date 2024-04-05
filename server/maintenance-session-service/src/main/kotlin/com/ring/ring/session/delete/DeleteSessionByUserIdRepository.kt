package com.ring.ring.session.delete

internal class DeleteSessionByUserIdRepository(
    private val remoteDataSource: DeleteSessionByUserIdRemoteDataSource,
    private val localDataSource: DeleteSessionByUserIdDataSource,
) {
    suspend fun delete(userId: Long) {
        localDataSource.delete(userId)
        remoteDataSource.delete(userId)
    }
}