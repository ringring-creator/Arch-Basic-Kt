package com.ring.ring.user.signup

import com.ring.ring.user.shared.SharedModules

internal object SignUpUserModules {
    val signUpUserRepository = createSignUpUserRepository()

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        queries = SharedModules.db.userQueries,
    )
}