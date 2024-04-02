package com.ring.ring.com.ring.ring.validate

class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)