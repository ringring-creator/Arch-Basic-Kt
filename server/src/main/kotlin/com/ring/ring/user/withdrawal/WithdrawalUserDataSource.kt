package com.ring.ring.user.withdrawal

import data.db.UserQueries

class WithdrawalUserDataSource(
    private val queries: UserQueries = WithdrawalUserModules.db.userQueries
) {
    fun delete(id: Long) = queries.delete(id)
}