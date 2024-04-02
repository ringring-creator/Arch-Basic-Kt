package com.ring.ring.todo

import com.ring.ring.todo.create.createTodoRouting
import com.ring.ring.todo.delete.deleteTodoRouting
import com.ring.ring.todo.edit.BadRequestException
import com.ring.ring.todo.edit.editTodoRouting
import com.ring.ring.todo.get.getTodoRouting
import com.ring.ring.todo.list.listTodoRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Application.todoModule() {
    configureSerialization()
    configureCors()
    configureStatusPages()
    todoConfigureRouting()
}

private fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
            ContentType.Application.Json
        )
    }
}

private fun Application.configureCors() {
    install(CORS) {
        anyHost()
        allowCredentials = true
        allowNonSimpleContentTypes = true
        allowHeaders { true }
    }
}

private fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is BadRequestException -> {
                    call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                }

                else -> {
                    call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}

private fun Application.todoConfigureRouting() {
    routing {
        createTodoRouting()
        deleteTodoRouting()
        editTodoRouting()
        getTodoRouting()
        listTodoRouting()
    }
}