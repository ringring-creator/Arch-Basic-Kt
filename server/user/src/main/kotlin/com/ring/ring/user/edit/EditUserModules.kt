package com.ring.ring.user.edit

import com.ring.ring.user.shared.SharedModules

internal object EditUserModules {
    val editUserRepository = createEditUserRepository()

    private fun createEditUserRepository(): EditUserRepository =
        EditUserRepository(
            dataSource = createEditUserDataSource()
        )

    private fun createEditUserDataSource(): EditUserDataSource = EditUserDataSource(
        queries = SharedModules.db.userQueries
    )
}