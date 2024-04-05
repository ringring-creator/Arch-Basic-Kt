package com.ring.ring.user.edit

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import user.shared.UserQueries

internal class EditUserRepository(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        val id = user.id ?: throw IllegalStateException()
        queries.update(
            email = user.email,
            password = user.password,
            id = id,
        )
    }
}