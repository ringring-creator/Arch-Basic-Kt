package com.ring.ring.com.ring.ring.todo

class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)