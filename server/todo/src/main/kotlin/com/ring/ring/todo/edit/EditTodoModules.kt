package com.ring.ring.todo.edit

import com.ring.ring.data.db.DataModules

internal object EditTodoModules {
    val editTodoRepository = createEditTodoRepository()

    private fun createEditTodoRepository(): EditTodoRepository = EditTodoRepository(
        dataSource = DataModules.todoDataSource
    )
}