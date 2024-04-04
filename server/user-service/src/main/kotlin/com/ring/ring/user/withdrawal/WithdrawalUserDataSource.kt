package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.SharedModules
import user.shared.UserQueries

internal class WithdrawalUserDataSource(
    private val queries: UserQueries = SharedModules.db.userQueries
) {
    fun delete(id: Long) = queries.delete(id)
}