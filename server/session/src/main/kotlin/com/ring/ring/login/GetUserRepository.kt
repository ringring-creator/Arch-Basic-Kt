package com.ring.ring.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserRepository(
    private val dataSource: GetUserDataSource = LoginModules.getUserDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }
}