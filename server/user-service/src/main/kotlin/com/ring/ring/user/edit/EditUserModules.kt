package com.ring.ring.user.edit

import com.ring.ring.user.shared.SharedModules

internal object EditUserModules {
    val editUserRepository = createEditUserRepository()

    private fun createEditUserRepository(): EditUserRepository =
        EditUserRepository(
            queries = SharedModules.db.userQueries
        )
}