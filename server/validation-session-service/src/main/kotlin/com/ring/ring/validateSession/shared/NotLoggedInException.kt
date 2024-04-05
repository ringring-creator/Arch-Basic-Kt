package com.ring.ring.validateSession.shared

internal class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)