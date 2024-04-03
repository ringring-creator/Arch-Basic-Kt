package com.ring.ring.user.exist

import com.ring.ring.data.db.DataModules
import com.ring.ring.user.shared.User
import data.db.UserQueries

internal class ExistUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun exist(user: User): Long? = queries
        .selectIdByEmailAndPassword(user.email, user.password)
        .executeAsOneOrNull()
}