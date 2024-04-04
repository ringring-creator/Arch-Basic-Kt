package com.ring.ring.user.edit

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import user.shared.UserQueries

internal class EditUserDataSource(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    fun update(user: User) {
        val id = user.id ?: throw IllegalStateException()
        queries.update(
            email = user.email,
            password = user.password,
            id = id,
        )
    }
}