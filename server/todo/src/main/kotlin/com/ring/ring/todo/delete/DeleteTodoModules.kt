package com.ring.ring.todo.delete

import com.ring.ring.data.db.DataModules

internal object DeleteTodoModules {
    val deleteTodoRepository = createDeleteTodoRepository()

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        dataSource = DataModules.todoDataSource,
    )
}