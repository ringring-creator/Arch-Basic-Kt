package com.ring.ring.session.login

import com.ring.ring.data.db.DataModules
import com.ring.ring.user.get.GetUserDataSource
import com.ring.ring.user.get.GetUserRepository

object LoginModules {
    val getUserRepository = createGetUserRepository()
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