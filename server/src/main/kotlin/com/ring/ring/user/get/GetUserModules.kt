package com.ring.ring.user.get

import com.ring.ring.data.db.DataModules

object GetUserModules {
    val getUserRepository = createGetUserRepository()
    val getUserDataSource = createGetUserDataSource()

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): GetUserDataSource = GetUserDataSource(
        queries = DataModules.db.userQueries
    )
}