package com.ring.ring.user.signup

import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SignUpUserRepository(
    private val dataSource: SignUpUserDataSource,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.insert(user = user)
    }
}