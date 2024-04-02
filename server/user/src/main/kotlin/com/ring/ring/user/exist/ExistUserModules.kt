package com.ring.ring.user.exist

import com.ring.ring.data.db.DataModules

object ExistUserModules {
    val getUserRepository = createGetUserRepository()
    val getUserDataSource = createGetUserDataSource()

    private fun createGetUserRepository(): ExistUserRepository = ExistUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): ExistUserDataSource = ExistUserDataSource(
        queries = DataModules.db.userQueries
    )
}