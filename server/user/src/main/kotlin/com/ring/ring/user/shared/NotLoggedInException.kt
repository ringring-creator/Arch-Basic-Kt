package com.ring.ring.user.shared

class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)