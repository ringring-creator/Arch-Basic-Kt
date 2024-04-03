package com.ring.ring.user.signup

import com.ring.ring.user.shared.SharedModules

internal object SignUpUserModules {
    val signUpUserRepository = createSignUpUserRepository()

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        dataSource = createSignUpUserDataSource()
    )

    private fun createSignUpUserDataSource(): SignUpUserDataSource = SignUpUserDataSource(
        queries = SharedModules.db.userQueries
    )
}