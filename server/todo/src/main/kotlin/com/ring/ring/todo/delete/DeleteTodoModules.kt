package com.ring.ring.todo.delete

import com.ring.ring.com.ring.ring.todo.delete.DeleteTodoDataSource
import com.ring.ring.data.db.DataModules

object DeleteTodoModules {
    val deleteTodoRepository = createDeleteTodoRepository()

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource(): DeleteTodoDataSource = DeleteTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}