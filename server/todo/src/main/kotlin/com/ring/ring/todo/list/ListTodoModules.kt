package com.ring.ring.todo.list

import com.ring.ring.data.db.DataModules

internal object ListTodoModules {
    val listTodoRepository = createListTodoRepository()

    private fun createListTodoRepository(): ListTodoRepository = ListTodoRepository(
        dataSource = DataModules.todoDataSource
    )
}