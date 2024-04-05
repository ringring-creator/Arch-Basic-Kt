package com.ring.ring.todo.get

import com.ring.ring.todo.shared.SharedModules

internal object GetTodoModules {
    val getTodoRepository = createGetTodoRepository()

    private fun createGetTodoRepository(): GetTodoRepository = GetTodoRepository(
        queries = SharedModules.db.todoQueries
    )
}