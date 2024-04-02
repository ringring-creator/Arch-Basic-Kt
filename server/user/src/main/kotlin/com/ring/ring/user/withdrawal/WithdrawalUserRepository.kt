package com.ring.ring.com.ring.ring.withdrawal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WithdrawalUserRepository(
    private val dataSource: WithdrawalUserDataSource = WithdrawalUserModules.withdrawalUserDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}