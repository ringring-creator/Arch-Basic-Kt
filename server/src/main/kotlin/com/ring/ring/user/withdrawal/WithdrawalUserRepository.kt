package com.ring.ring.user.withdrawal

import com.ring.ring.di.DataModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WithdrawalUserRepository(
    private val dataSource: WithdrawalUserDataSource = DataModules.withdrawalUserDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}