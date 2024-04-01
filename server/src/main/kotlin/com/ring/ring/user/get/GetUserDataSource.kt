package com.ring.ring.user.get

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
import data.db.UserQueries
import data.db.UserTable

class GetUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun get(id: Long): User = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    fun loadId(user: User): Long? = queries
        .selectIdByEmailAndPassword(user.email, user.password)
        .executeAsOneOrNull()

    private fun convert(table: UserTable) = User(
        id = table.id,
        email = table.email,
        password = table.password,
    )
}