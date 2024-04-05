package com.ring.ring.user.exist

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import user.shared.UserQueries

internal class ExistUserRepository(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    suspend fun exist(user: User): Long? = withContext(Dispatchers.IO) {
        return@withContext queries
            .selectIdByEmailAndPassword(user.email, user.password)
            .executeAsOneOrNull()
    }
}