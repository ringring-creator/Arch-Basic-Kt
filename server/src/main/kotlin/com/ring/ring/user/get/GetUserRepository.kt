package com.ring.ring.user.get

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
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