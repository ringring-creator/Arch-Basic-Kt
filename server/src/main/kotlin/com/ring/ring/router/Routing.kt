package com.ring.ring.router

import com.ring.ring.session.login.loginSessionRouting
import com.ring.ring.session.logout.logoutSessionRouting
import com.ring.ring.session.validate.validateSessionRouting
import com.ring.ring.todo.create.createTodoRouting
import com.ring.ring.todo.delete.deleteTodoRouting
import com.ring.ring.todo.edit.editTodoRouting
import com.ring.ring.todo.get.getTodoRouting
import com.ring.ring.todo.list.listTodoRouting
import com.ring.ring.user.edit.editUserRouting
import com.ring.ring.user.get.getUserRouting
import com.ring.ring.user.signup.signUpUserRestApiRouting
import com.ring.ring.user.withdrawal.withdrawalUserRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        editUserRouting()
        getUserRouting()
        signUpUserRestApiRouting()
        withdrawalUserRouting()
        loginSessionRouting()
        logoutSessionRouting()
        validateSessionRouting()
        createTodoRouting()
        deleteTodoRouting()
        editTodoRouting()
        getTodoRouting()
        listTodoRouting()
    }
}