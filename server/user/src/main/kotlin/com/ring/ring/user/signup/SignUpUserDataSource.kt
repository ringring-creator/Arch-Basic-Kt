package com.ring.ring.user.signup

import com.ring.ring.user.shared.SharedModules
import com.ring.ring.user.shared.User
import user.shared.UserQueries

internal class SignUpUserDataSource(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    fun insert(user: User) = queries.insert(
        email = user.email,
        password = user.password,
    )
}