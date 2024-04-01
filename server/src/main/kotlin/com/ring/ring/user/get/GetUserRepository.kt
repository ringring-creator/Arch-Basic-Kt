package com.ring.ring.user.get

import com.ring.ring.di.DataModules
import com.ring.ring.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserRepository(
    private val dataSource: GetUserDataSource = DataModules.getUserDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }
}