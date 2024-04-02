package com.ring.ring.com.ring.ring.todo.create

import com.ring.ring.data.db.DataModules
import com.ring.ring.todo.create.CreateTodoDataSource
import com.ring.ring.todo.create.CreateTodoRepository

object CreateTodoModules {
    val createTodoRepository = createCreateTodoRepository()

    private fun createCreateTodoRepository(): CreateTodoRepository = CreateTodoRepository(
        dataSource = createCreateTodoDataSource()
    )

    private fun createCreateTodoDataSource(): CreateTodoDataSource = CreateTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}