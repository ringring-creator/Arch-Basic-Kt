package com.ring.ring.router

import com.ring.ring.todo.create.createTodoRouting
import com.ring.ring.todo.delete.deleteTodoRouting
import com.ring.ring.todo.edit.editTodoRouting
import com.ring.ring.todo.get.getTodoRouting
import com.ring.ring.todo.list.listTodoRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRestApiRouting()
        sessionRestApiRouting()
        createTodoRouting()
        deleteTodoRouting()
        editTodoRouting()
        getTodoRouting()
        listTodoRouting()
    }
}