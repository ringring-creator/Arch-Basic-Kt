package com.ring.ring.user.exist

import com.ring.ring.data.db.User
import com.ring.ring.data.db.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ExistUserRepository(
    private val dataSource: UserDataSource,
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.exist(user = user)
    }
}