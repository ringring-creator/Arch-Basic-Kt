package com.ring.ring.user.edit

import com.ring.ring.data.db.DataModules

object EditUserModules {
    val editUserRepository = createEditUserRepository()
    val editUserDataSource = createEditUserDataSource()

    private fun createEditUserRepository(): EditUserRepository = EditUserRepository(
        dataSource = createEditUserDataSource()
    )

    private fun createEditUserDataSource(): EditUserDataSource = EditUserDataSource(
        queries = DataModules.db.userQueries
    )
}