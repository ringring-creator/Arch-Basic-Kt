package com.ring.ring.user.exist

import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ExistUserRepository(
    private val dataSource: ExistUserDataSource
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.exist(user = user)
    }
}