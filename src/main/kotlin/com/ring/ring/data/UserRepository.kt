package com.ring.ring.data

import com.ring.ring.data.db.User
import com.ring.ring.data.db.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val dataSource: UserDataSource = UserDataSource(),
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.upsert(user = user)
    }
}