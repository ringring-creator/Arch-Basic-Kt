package com.ring.ring.user.signup

import com.ring.ring.data.db.User
import com.ring.ring.data.db.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SignUpUserRepository(
    private val dataSource: UserDataSource,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.insert(user = user)
    }
}