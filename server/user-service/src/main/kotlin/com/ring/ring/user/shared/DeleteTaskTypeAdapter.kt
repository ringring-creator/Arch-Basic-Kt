package com.ring.ring.user.shared

import app.cash.sqldelight.ColumnAdapter

class DeleteTaskTypeAdapter : ColumnAdapter<DeleteTask.Type, String> {
    override fun decode(databaseValue: String): DeleteTask.Type {
        return when (databaseValue) {
            "todo" -> DeleteTask.Type.Todo
            "user" -> DeleteTask.Type.User
            "session" -> DeleteTask.Type.Session
            else -> throw IllegalStateException()
        }
    }

    override fun encode(value: DeleteTask.Type): String {
        return when (value) {
            DeleteTask.Type.Todo -> "todo"
            DeleteTask.Type.User -> "user"
            DeleteTask.Type.Session -> "session"
        }
    }
}