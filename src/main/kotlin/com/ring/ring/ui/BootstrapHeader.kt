package com.ring.ring.ui

import kotlinx.html.HEAD
import kotlinx.html.link

fun HEAD.bootstrap() {
    link(
        rel = "stylesheet",
        href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css",
        type = "text/css"
    )
}