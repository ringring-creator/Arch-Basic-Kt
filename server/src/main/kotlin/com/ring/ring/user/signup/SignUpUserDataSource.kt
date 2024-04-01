package com.ring.ring.user.signup

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
import data.db.UserQueries

class SignUpUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun insert(user: User) = queries.insert(
        email = user.email,
        password = user.password,
    )
}