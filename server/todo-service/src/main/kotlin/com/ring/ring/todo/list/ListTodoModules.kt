package com.ring.ring.todo.list

import com.ring.ring.todo.shared.SharedModules

internal object ListTodoModules {
    val listTodoRepository = createListTodoRepository()

    private fun createListTodoRepository(): ListTodoRepository = ListTodoRepository(
        queries = SharedModules.db.todoQueries,
    )
}