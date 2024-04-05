package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.SharedModules

internal object WithdrawalUserModules {
    val withdrawalUserRepository = createWithdrawalUserRepository()
    val todoRepository = createTodoRepository()
    val sessionRepository = createDeleteSessionRepository()

    private fun createDeleteSessionRepository() = DeleteSessionRepository(
        dataSource = createDeleteSessionDataSource(),
    )

    private fun createDeleteSessionDataSource() = DeleteSessionDataSource(
        httpClient = SharedModules.httpClient,
    )

    private fun createTodoRepository() = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource() = DeleteTodoDataSource(
        httpClient = SharedModules.httpClient
    )

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): WithdrawalUserDataSource = WithdrawalUserDataSource(
        queries = SharedModules.db.userQueries
    )
}