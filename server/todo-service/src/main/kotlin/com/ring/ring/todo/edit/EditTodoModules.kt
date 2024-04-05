package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.SharedModules

internal object EditTodoModules {
    val editTodoRepository = createEditTodoRepository()

    private fun createEditTodoRepository(): EditTodoRepository = EditTodoRepository(
        queries = SharedModules.db.todoQueries,
    )
}