package com.ring.ring.user.edit

import com.ring.ring.data.db.DataModules

internal object EditUserModules {
    val editUserRepository = createEditUserRepository()

    private fun createEditUserRepository() = EditUserRepository(
        dataSource = DataModules.userDataSource,
    )
}