package com.ring.ring.user.get

import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetUserRepository(
    private val dataSource: GetUserDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

}