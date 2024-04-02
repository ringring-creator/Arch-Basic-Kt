package com.ring.ring.user.signup

import com.ring.ring.data.db.DataModules

object SignUpUserModules {
    val signUpUserRepository = createSignUpUserRepository()
    val signUpUserDataSource = createSignUpUserDataSource()

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        dataSource = createSignUpUserDataSource()
    )

    private fun createSignUpUserDataSource(): SignUpUserDataSource = SignUpUserDataSource(
        queries = DataModules.db.userQueries
    )
}