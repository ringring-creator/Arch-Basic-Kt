package com.ring.ring.user.get

import com.ring.ring.data.db.User
import com.ring.ring.data.db.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetUserRepository(
    private val dataSource: UserDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

}