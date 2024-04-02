package com.ring.ring.user.get

import com.ring.ring.data.db.DataModules
import com.ring.ring.user.User
import data.db.UserQueries
import data.db.UserTable

internal class GetUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
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