package com.ring.ring.todo.create

import com.ring.ring.data.db.DataModules

internal object CreateTodoModules {
    val createTodoRepository = createCreateTodoRepository()

    private fun createCreateTodoRepository(): CreateTodoRepository = CreateTodoRepository(
        dataSource = createCreateTodoDataSource()
    )

    private fun createCreateTodoDataSource(): CreateTodoDataSource = CreateTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}