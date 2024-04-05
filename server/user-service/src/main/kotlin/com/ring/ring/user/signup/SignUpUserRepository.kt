package com.ring.ring.user.signup

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import user.shared.UserQueries

internal class SignUpUserRepository(
    private val queries: UserQueries = SharedModules.db.userQueries,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        queries.insert(
            email = user.email,
            password = user.password,
        )
    }
}