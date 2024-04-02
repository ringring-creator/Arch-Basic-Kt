package com.ring.ring.user.signup

import com.ring.ring.data.db.DataModules
import com.ring.ring.user.User
import data.db.UserQueries

class SignUpUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun insert(user: User) = queries.insert(
        email = user.email,
        password = user.password,
    )
}