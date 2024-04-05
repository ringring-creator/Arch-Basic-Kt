package com.ring.ring.user.withdrawal

import com.ring.ring.data.db.DataModules
import com.ring.ring.data.db.UserDataSource

internal object WithdrawalUserModules {
    val withdrawalUserRepository = createWithdrawalUserRepository()
    val withdrawalUserDataSource = createWithdrawalUserDataSource()

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): UserDataSource = UserDataSource(
        queries = DataModules.db.userQueries
    )
}