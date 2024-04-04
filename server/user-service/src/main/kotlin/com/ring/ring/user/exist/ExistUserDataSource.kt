package com.ring.ring.user.exist

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import user.shared.UserQueries

internal class ExistUserDataSource(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    fun exist(user: User): Long? = queries
        .selectIdByEmailAndPassword(user.email, user.password)
        .executeAsOneOrNull()
}