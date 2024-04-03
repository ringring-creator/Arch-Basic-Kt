package com.ring.ring.user.withdrawal

import com.ring.ring.data.db.DataModules

internal object WithdrawalUserModules {
    val withdrawalUserRepository = createWithdrawalUserRepository()

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): WithdrawalUserDataSource = WithdrawalUserDataSource(
        queries = DataModules.db.userQueries
    )
}