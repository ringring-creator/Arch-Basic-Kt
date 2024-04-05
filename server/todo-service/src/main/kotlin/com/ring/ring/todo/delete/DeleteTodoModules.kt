package com.ring.ring.todo.delete

import com.ring.ring.todo.shared.SharedModules

internal object DeleteTodoModules {
    val deleteTodoRepository = createDeleteTodoRepository()

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        queries = SharedModules.db.todoQueries,
    )
}