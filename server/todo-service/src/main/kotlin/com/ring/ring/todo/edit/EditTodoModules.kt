package com.ring.ring.todo.edit

import com.ring.ring.todo.shared.SharedModules

internal object EditTodoModules {
    val editTodoRepository = createEditTodoRepository()

    private fun createEditTodoRepository(): EditTodoRepository = EditTodoRepository(
        dataSource = createEditTodoDataSource()
    )

    private fun createEditTodoDataSource(): EditTodoDataSource = EditTodoDataSource(
        queries = SharedModules.db.todoQueries
    )
}