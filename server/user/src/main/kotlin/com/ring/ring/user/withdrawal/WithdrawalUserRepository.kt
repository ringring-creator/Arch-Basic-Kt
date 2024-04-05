package com.ring.ring.user.withdrawal

import com.ring.ring.data.db.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class WithdrawalUserRepository(
    private val dataSource: UserDataSource = WithdrawalUserModules.withdrawalUserDataSource,
) {
    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}