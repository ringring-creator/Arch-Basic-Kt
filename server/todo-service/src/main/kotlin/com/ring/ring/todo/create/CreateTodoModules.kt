package com.ring.ring.todo.create

import com.ring.ring.todo.shared.SharedModules


internal object CreateTodoModules {
    val createTodoRepository = createCreateTodoRepository()

    private fun createCreateTodoRepository(): CreateTodoRepository = CreateTodoRepository(
        queries = SharedModules.db.todoQueries,
    )
}