package com.ring.ring.todo.delete

import com.ring.ring.todo.shared.SharedModules

internal object DeleteTodoModules {
    val deleteTodoRepository = createDeleteTodoRepository()

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource(): DeleteTodoDataSource = DeleteTodoDataSource(
        queries = SharedModules.db.todoQueries
    )
}