package com.ring.ring.user.get

import com.ring.ring.user.shared.SharedModules

internal object GetUserModules {
    val getUserRepository = createGetUserRepository()

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): GetUserDataSource = GetUserDataSource(
        queries = SharedModules.db.userQueries
    )
}