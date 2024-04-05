package com.ring.ring.todo.get

import com.ring.ring.data.db.DataModules
import com.ring.ring.data.db.TodoDataSource

internal object GetTodoModules {
    val getTodoRepository = createGetTodoRepository()

    private fun createGetTodoRepository(): GetTodoRepository = GetTodoRepository(
        dataSource = createGetTodoDataSource()
    )

    private fun createGetTodoDataSource(): TodoDataSource = TodoDataSource(
        queries = DataModules.db.todoQueries
    )
}