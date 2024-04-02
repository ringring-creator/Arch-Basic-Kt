package com.ring.ring.com.ring.ring.withdrawal

import com.ring.ring.data.db.DataModules
import data.db.UserQueries

class WithdrawalUserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun delete(id: Long) = queries.delete(id)
}