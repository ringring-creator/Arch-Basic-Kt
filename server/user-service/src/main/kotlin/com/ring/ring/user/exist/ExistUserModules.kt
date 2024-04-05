package com.ring.ring.user.exist

import com.ring.ring.user.shared.SharedModules

internal object ExistUserModules {
    val getUserRepository = createGetUserRepository()

    private fun createGetUserRepository(): ExistUserRepository = ExistUserRepository(
        queries = SharedModules.db.userQueries,
    )
}