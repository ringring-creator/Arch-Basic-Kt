package com.ring.ring.session.shared

internal class NotLoggedInException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)