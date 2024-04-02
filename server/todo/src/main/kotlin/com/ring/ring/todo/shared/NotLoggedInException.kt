package com.ring.ring.todo.shared

class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)