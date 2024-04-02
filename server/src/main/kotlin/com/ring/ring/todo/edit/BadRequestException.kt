package com.ring.ring.todo.edit

class BadRequestException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)