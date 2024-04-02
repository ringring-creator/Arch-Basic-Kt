package com.ring.ring.com.ring.ring.todo.get

import com.ring.ring.data.db.DataModules
import com.ring.ring.todo.get.GetTodoDataSource
import com.ring.ring.todo.get.GetTodoRepository

object GetTodoModules {
    val getTodoRepository = createGetTodoRepository()

    private fun createGetTodoRepository(): GetTodoRepository = GetTodoRepository(
        dataSource = createGetTodoDataSource()
    )

    private fun createGetTodoDataSource(): GetTodoDataSource = GetTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}