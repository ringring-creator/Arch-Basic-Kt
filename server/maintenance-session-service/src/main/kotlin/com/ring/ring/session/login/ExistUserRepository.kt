package com.ring.ring.session.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ExistUserRepository(
    private val dataSource: ExistUserRemoteDataSource,
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.exist(user).userId
    }
}