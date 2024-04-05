package com.ring.ring.user.get

import com.ring.ring.data.db.DataModules

internal object GetUserModules {
    val getUserRepository = createGetUserRepository()

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = DataModules.userDataSource
    )
}