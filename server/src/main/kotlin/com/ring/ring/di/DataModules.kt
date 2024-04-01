package com.ring.ring.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import com.ring.ring.data.db.SessionDataSource
import com.ring.ring.data.db.UserDataSource
import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.data.repository.UserRepository
import com.ring.ring.todo.create.CreateTodoDataSource
import com.ring.ring.todo.create.CreateTodoRepository
import com.ring.ring.todo.delete.DeleteTodoDataSource
import com.ring.ring.todo.delete.DeleteTodoRepository
import com.ring.ring.todo.edit.EditTodoDataSource
import com.ring.ring.todo.edit.EditTodoRepository
import com.ring.ring.todo.get.GetTodoDataSource
import com.ring.ring.todo.get.GetTodoRepository
import com.ring.ring.todo.list.ListTodoDataSource
import com.ring.ring.todo.list.ListTodoRepository
import data.db.LocalDb
import data.db.TodoTable
import java.util.*

object DataModules {
    val db = createDb()
    val createTodoRepository = createCreateTodoRepository()
    val deleteTodoRepository = createDeleteTodoRepository()
    val editTodoRepository = createEditTodoRepository()
    val getTodoRepository = createGetTodoRepository()
    val listTodoRepository = createListTodoRepository()
    val userRepository = createUserRepository()
    val userDataSource = createUserDataSource()
    val sessionRepository = createSessionRepository()
    val sessionDataSource = createSessionDataSource()

    private fun createCreateTodoRepository(): CreateTodoRepository = CreateTodoRepository(
        dataSource = createCreateTodoDataSource()
    )

    private fun createCreateTodoDataSource(): CreateTodoDataSource = CreateTodoDataSource(
        queries = db.todoQueries
    )

    private fun createDeleteTodoRepository(): DeleteTodoRepository = DeleteTodoRepository(
        dataSource = createDeleteTodoDataSource()
    )

    private fun createDeleteTodoDataSource(): DeleteTodoDataSource = DeleteTodoDataSource(
        queries = db.todoQueries
    )

    private fun createEditTodoRepository(): EditTodoRepository = EditTodoRepository(
        dataSource = createEditTodoDataSource()
    )

    private fun createEditTodoDataSource(): EditTodoDataSource = EditTodoDataSource(
        queries = db.todoQueries
    )

    private fun createGetTodoRepository(): GetTodoRepository = GetTodoRepository(
        dataSource = createGetTodoDataSource()
    )

    private fun createGetTodoDataSource(): GetTodoDataSource = GetTodoDataSource(
        queries = db.todoQueries
    )

    private fun createListTodoRepository(): ListTodoRepository = ListTodoRepository(
        dataSource = createListTodoDataSource()
    )

    private fun createListTodoDataSource(): ListTodoDataSource = ListTodoDataSource(
        queries = db.todoQueries
    )

    private fun createUserRepository(): UserRepository = UserRepository(
        dataSource = createUserDataSource()
    )

    private fun createUserDataSource(): UserDataSource = UserDataSource(
        queries = db.userQueries
    )

    private fun createSessionRepository(): SessionRepository = SessionRepository(
        dataSource = createSessionDataSource()
    )

    private fun createSessionDataSource(): SessionDataSource = SessionDataSource(
        queries = db.sessionQueries
    )

    private fun createDb() = LocalDb(
        driver = createSqliteDriver(),
        TodoTableAdapter = createTodoTableAdapter()
    )

    private fun createTodoTableAdapter() = TodoTable.Adapter(
        deadlineAdapter = createDeadlineAdapter()
    )

    private fun createDeadlineAdapter() = DeadlineAdapter()

    private fun createSqliteDriver(): SqlDriver = JdbcSqliteDriver(
        url = "jdbc:sqlite:db/database.db",
        schema = LocalDb.Schema,
        properties = Properties().apply { put("foreign_keys", "true") }
    )

    private fun createInMemorySqliteDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        LocalDb.Schema.create(driver)
        return driver
    }
}