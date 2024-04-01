package com.ring.ring.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ring.ring.data.db.DeadlineAdapter
import com.ring.ring.session.login.InsertSessionDataSource
import com.ring.ring.session.login.SaveSessionRepository
import com.ring.ring.session.logout.DeleteSessionDataSource
import com.ring.ring.session.logout.DeleteSessionRepository
import com.ring.ring.session.validate.ValidateSessionDataSource
import com.ring.ring.session.validate.ValidateSessionRepository
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
import com.ring.ring.user.edit.EditUserDataSource
import com.ring.ring.user.edit.EditUserRepository
import com.ring.ring.user.get.GetUserDataSource
import com.ring.ring.user.get.GetUserRepository
import com.ring.ring.user.signup.SignUpUserDataSource
import com.ring.ring.user.signup.SignUpUserRepository
import com.ring.ring.user.withdrawal.WithdrawalUserDataSource
import com.ring.ring.user.withdrawal.WithdrawalUserRepository
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
    val editUserRepository = createEditUserRepository()
    val editUserDataSource = createEditUserDataSource()
    val getUserRepository = createGetUserRepository()
    val getUserDataSource = createGetUserDataSource()
    val signUpUserRepository = createSignUpUserRepository()
    val signUpUserDataSource = createSignUpUserDataSource()
    val withdrawalUserRepository = createWithdrawalUserRepository()
    val withdrawalUserDataSource = createWithdrawalUserDataSource()
    val saveSessionRepository = createSaveSessionRepository()
    val insertSessionDataSource = createInsertSessionDataSource()
    val deleteSessionRepository = createDeleteSessionRepository()
    val deleteSessionDataSource = createDeleteSessionDataSource()
    val validateSessionRepository = createValidateSessionRepository()
    val validateSessionDataSource = createValidateSessionDataSource()

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

    private fun createEditUserRepository(): EditUserRepository = EditUserRepository(
        dataSource = createEditUserDataSource()
    )

    private fun createEditUserDataSource(): EditUserDataSource = EditUserDataSource(
        queries = db.userQueries
    )

    private fun createGetUserRepository(): GetUserRepository = GetUserRepository(
        dataSource = createGetUserDataSource()
    )

    private fun createGetUserDataSource(): GetUserDataSource = GetUserDataSource(
        queries = db.userQueries
    )

    private fun createSignUpUserRepository(): SignUpUserRepository = SignUpUserRepository(
        dataSource = createSignUpUserDataSource()
    )

    private fun createSignUpUserDataSource(): SignUpUserDataSource = SignUpUserDataSource(
        queries = db.userQueries
    )

    private fun createWithdrawalUserRepository(): WithdrawalUserRepository = WithdrawalUserRepository(
        dataSource = createWithdrawalUserDataSource()
    )

    private fun createWithdrawalUserDataSource(): WithdrawalUserDataSource = WithdrawalUserDataSource(
        queries = db.userQueries
    )

    private fun createSaveSessionRepository(): SaveSessionRepository = SaveSessionRepository(
        dataSource = createInsertSessionDataSource()
    )

    private fun createInsertSessionDataSource(): InsertSessionDataSource = InsertSessionDataSource(
        queries = db.sessionQueries
    )

    private fun createDeleteSessionRepository(): DeleteSessionRepository = DeleteSessionRepository(
        dataSource = createDeleteSessionDataSource()
    )

    private fun createDeleteSessionDataSource(): DeleteSessionDataSource = DeleteSessionDataSource(
        queries = db.sessionQueries
    )

    private fun createValidateSessionRepository(): ValidateSessionRepository = ValidateSessionRepository(
        dataSource = createValidateSessionDataSource()
    )

    private fun createValidateSessionDataSource(): ValidateSessionDataSource = ValidateSessionDataSource(
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