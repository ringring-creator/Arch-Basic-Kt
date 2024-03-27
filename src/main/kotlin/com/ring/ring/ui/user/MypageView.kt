package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.user.GetUser
import kotlinx.html.*

fun HTML.mypageView(res: GetUser.Res) {
    head {
        title {
            buildString {
                append("My Page")
            }
        }
        bootstrap()
    }
    body {
        userNavBar()
        div(classes = "d-flex justify-content-center") {
            style = "min-height: 100vh; padding: 20px;"
            div(classes = "w-100 mt-5 text-center") {
                h1(classes = "h3 mb-3 fw-normal") { +"My Page" }
                p { +"Email: ${res.user.email}" }
                div(classes = "d-grid gap-2 col-6 mx-auto") {
                    form(
                        action = "logout",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.get
                    ) {
                        button(classes = "btn btn-primary btn-lg w-50", type = ButtonType.submit) {
                            +"Logout"
                        }
                    }
                    form(
                        action = "edit",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.get
                    ) {
                        button(classes = "btn btn-secondary btn-lg w-50", type = ButtonType.submit) {
                            +"Edit"
                        }
                    }
                    form(
                        action = "withdrawal",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.get
                    ) {
                        hiddenInput(name = "id") { value = res.user.id }
                        button(classes = "btn btn-danger btn-lg w-50", type = ButtonType.submit) {
                            +"Withdrawal"
                        }
                    }
                }
            }
        }
    }
}