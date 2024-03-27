package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.logoutView() {
    head {
        title {
            buildString {
                append("Logout")
            }
        }
        bootstrap()
    }
    body {
        userNavBar()
        div(classes = "d-flex justify-content-center") {
            style = "min-height: 100vh; padding: 20px;"
            div(classes = "text-center") {
                h1(classes = "h3 mb-3 fw-normal") { +"Logout" }
                p(classes = "mt-5 mb-3 text-muted text-center") { +"Do you want to logout?" }
                div(classes = "d-inline-block") {
                    form(
                        action = "api/logout",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post,
                        classes = "d-inline",
                    ) {
                        button(classes = "btn btn-lg btn-primary me-2", type = ButtonType.submit) {
                            +"Yes"
                        }
                    }
                    form(
                        action = "mypage",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.get,
                        classes = "d-inline",
                    ) {
                        button(classes = "btn btn-lg btn-secondary", type = ButtonType.submit) {
                            +"No"
                        }
                    }
                }
            }
        }
    }
}