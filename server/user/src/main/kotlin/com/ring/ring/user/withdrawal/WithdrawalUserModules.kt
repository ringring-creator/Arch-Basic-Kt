package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.SharedModules

internal object WithdrawalUserModules {
    val withdrawalUserRepository = createWithdrawalUserRepository()

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): WithdrawalUserDataSource = WithdrawalUserDataSource(
        queries = SharedModules.db.userQueries
    )
}