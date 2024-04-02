package com.ring.ring.user.edit

import com.ring.ring.user.User
import data.db.UserQueries

class EditUserDataSource(
    private val queries: UserQueries = EditUserModules.db.userQueries
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