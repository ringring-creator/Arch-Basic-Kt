package com.ring.ring

import com.ring.ring.controller.SessionController
import com.ring.ring.controller.TodoController
import com.ring.ring.controller.UserController
import com.ring.ring.di.ControllerModules
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRestApiRouting()
        sessionRestApiRouting()
        todoRestApiRouting()
    }
}

private fun Route.sessionRestApiRouting(
    controller: SessionController = ControllerModules.sessionController,
) {
    route("/session") {
        post("login") { controller.login(call) }
        post("logout") { controller.logout(call) }
    }
}

private fun Route.todoRestApiRouting(
    controller: TodoController = ControllerModules.todoController,
) {
    route("/todo") {
        post("create") { controller.create(call) }
        post("get") { controller.get(call) }
        post("list") { controller.list(call) }
        post("edit") { controller.edit(call) }
        post("delete") { controller.delete(call) }
        post("editDone") { controller.editDone(call) }
    }
}

private fun Route.userRestApiRouting(
    controller: UserController = ControllerModules.userController,
) {
    route("/user") {
        post("get") { controller.get(call) }
        post("signup") { controller.signUp(call) }
        post("edit") { controller.edit(call) }
        post("withdrawal") { controller.withdrawal(call) }
    }
}