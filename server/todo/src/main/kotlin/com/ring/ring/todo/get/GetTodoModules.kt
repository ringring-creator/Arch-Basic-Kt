package com.ring.ring.todo.get

import com.ring.ring.data.db.DataModules

internal object GetTodoModules {
    val getTodoRepository = createGetTodoRepository()

    private fun createGetTodoRepository(): GetTodoRepository = GetTodoRepository(
        dataSource = createGetTodoDataSource()
    )

    private fun createGetTodoDataSource(): GetTodoDataSource = GetTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}