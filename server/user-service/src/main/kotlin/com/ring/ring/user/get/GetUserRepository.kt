package com.ring.ring.user.get

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import user.shared.UserQueries
import user.shared.UserTable

internal class GetUserRepository(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        return@withContext queries
            .selectById(id)
            .executeAsOne()
            .let { convert(it) }
    }

    private fun convert(table: UserTable) = User(
        id = table.id,
        email = table.email,
        password = table.password,
    )
}