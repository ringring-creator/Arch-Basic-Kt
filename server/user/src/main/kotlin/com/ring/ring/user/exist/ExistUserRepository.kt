package com.ring.ring.user.exist

import com.ring.ring.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExistUserRepository(
    private val dataSource: ExistUserDataSource = ExistUserModules.getUserDataSource,
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.exist(user = user)
    }
}