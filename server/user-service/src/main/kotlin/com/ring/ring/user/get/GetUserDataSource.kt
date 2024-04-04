package com.ring.ring.user.get

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import user.shared.UserQueries
import user.shared.UserTable

internal class GetUserDataSource(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    fun get(id: Long): User = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    private fun convert(table: UserTable) = User(
        id = table.id,
        email = table.email,
        password = table.password,
    )
}