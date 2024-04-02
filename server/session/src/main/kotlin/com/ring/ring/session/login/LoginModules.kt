package com.ring.ring.session.login

import com.ring.ring.data.db.DataModules

object LoginModules {
    val getUserRepository = createGetUserRepository()
    val getUserDataSource = createGetUserDataSource()
    val saveSessionRepository = createSaveSessionRepository()
    val insertSessionDataSource = createInsertSessionDataSource()

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): GetUserDataSource = GetUserDataSource(
        queries = DataModules.db.userQueries
    )

    private fun createSaveSessionRepository(): SaveSessionRepository = SaveSessionRepository(
        dataSource = createInsertSessionDataSource()
    )

    private fun createInsertSessionDataSource(): InsertSessionDataSource = InsertSessionDataSource(
        queries = DataModules.db.sessionQueries
    )
}