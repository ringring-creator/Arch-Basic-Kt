package com.ring.ring.user.withdrawal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class WithdrawalUserRepository(
    private val dataSource: WithdrawalUserDataSource = WithdrawalUserModules.withdrawalUserDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}