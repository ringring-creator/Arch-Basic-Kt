package com.ring.ring.di

import com.ring.ring.controller.*

object ControllerModules {
    val sessionController: SessionController = createSessionRestApiController()
    val todoController: TodoController = createTodoRestApiController()
    val userController: UserController = createUserRestApiController()

    private fun createSessionRestApiController(): SessionController = SessionRestApiController()
    private fun createTodoRestApiController(): TodoController = TodoRestApiController()
    private fun createUserRestApiController(): UserController = UserRestApiController()
}