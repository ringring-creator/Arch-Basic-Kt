package com.ring.ring.session.validate

internal class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)