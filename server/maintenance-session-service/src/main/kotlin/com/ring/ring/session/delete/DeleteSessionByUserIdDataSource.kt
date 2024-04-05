package com.ring.ring.session.delete

import session.shared.SessionQueries

internal class DeleteSessionByUserIdDataSource(
    private val queries: SessionQueries
) {
    fun delete(userId: Long) = queries.deleteByUserId(
        userId = userId,
    )
}