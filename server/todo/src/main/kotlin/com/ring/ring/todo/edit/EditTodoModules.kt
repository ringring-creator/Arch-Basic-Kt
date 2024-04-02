package com.ring.ring.todo.edit

import com.ring.ring.data.db.DataModules

object EditTodoModules {
    val editTodoRepository = createEditTodoRepository()

    private fun createEditTodoRepository(): EditTodoRepository = EditTodoRepository(
        dataSource = createEditTodoDataSource()
    )

    private fun createEditTodoDataSource(): EditTodoDataSource = EditTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}