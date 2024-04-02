package com.ring.ring.session.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExistUserRepository(
    private val dataSource: ExistUserRemoteDataSource = LoginModules.getUserDataSource,
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.exist(user).userId
    }
}