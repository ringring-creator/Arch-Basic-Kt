package com.ring.ring.todo.list

import com.ring.ring.todo.shared.SharedModules

internal object ListTodoModules {
    val listTodoRepository = createListTodoRepository()

    private fun createListTodoRepository(): ListTodoRepository = ListTodoRepository(
        dataSource = createListTodoDataSource()
    )

    private fun createListTodoDataSource(): ListTodoDataSource = ListTodoDataSource(
        queries = SharedModules.db.todoQueries
    )
}