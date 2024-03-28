package com.ring.ring.ui.user

import kotlinx.html.*

fun BODY.userNavBar() {
    nav(classes = "navbar navbar-expand-lg navbar-dark bg-dark") {
        div(classes = "container-fluid") {
            a(classes = "navbar-brand") {
                +"Sample app"
                br
                +" for architecture basic"
            }
            button(classes = "navbar-toggler", type = ButtonType.button) {
                attributes["data-bs-toggle"] = "collapse"
                attributes["data-bs-target"] = "#navbarNav"
                attributes["aria-controls"] = "navbarNav"
                attributes["aria-expanded"] = "false"
                attributes["aria-label"] = "Toggle navigation"
                span(classes = "navbar-toggler-icon")
            }
            div(classes = "collapse navbar-collapse") {
                ul(classes = "navbar-nav me-auto") {
                    li(classes = "nav-item") {
                        a(classes = "nav-link", href = "../todo/list") { +"Todo" }
                    }
                    li(classes = "nav-item") {
                        a(classes = "nav-link active", href = "mypage") { +"My Page" }
                    }
                }
            }
        }
    }
}