package com.ring.ring.com.ring.ring.todo.list

import com.ring.ring.data.db.DataModules
import com.ring.ring.todo.list.ListTodoDataSource
import com.ring.ring.todo.list.ListTodoRepository

object ListTodoModules {
    val listTodoRepository = createListTodoRepository()

    private fun createListTodoRepository(): ListTodoRepository = ListTodoRepository(
        dataSource = createListTodoDataSource()
    )

    private fun createListTodoDataSource(): ListTodoDataSource = ListTodoDataSource(
        queries = DataModules.db.todoQueries
    )
}