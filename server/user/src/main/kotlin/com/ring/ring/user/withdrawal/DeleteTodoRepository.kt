package com.ring.ring.user.withdrawal

internal class DeleteTodoRepository(
    private val dataSource: DeleteTodoDataSource,
) {
    suspend fun delete(userId: Long) {
        dataSource.delete(userId)
    }
}