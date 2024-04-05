package com.ring.ring.user.signup

import com.ring.ring.data.db.DataModules

internal object SignUpUserModules {
    val signUpUserRepository = createSignUpUserRepository()

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        dataSource = DataModules.userDataSource
    )
}