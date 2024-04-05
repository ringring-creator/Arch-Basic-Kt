package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.DeleteTask
import com.ring.ring.user.shared.SharedModules
import user.shared.DeleteTaskQueries
import user.shared.DeleteTaskTable

internal class DeleteTaskRepository(
    private val queries: DeleteTaskQueries = SharedModules.db.deleteTaskQueries
) {
    fun getTasks(): List<DeleteTask> = queries
        .selectAll()
        .executeAsList()
        .map { convert(it) }

    fun save(deleteTask: DeleteTask) {
        queries.insert(deleteTask.type, deleteTask.userId)
    }

    fun delete(deleteTask: DeleteTask) {
        queries.delete(deleteTask.type, deleteTask.userId)
    }

    private fun convert(table: DeleteTaskTable) = DeleteTask(
        type = table.type,
        userId = table.userId
    )
}