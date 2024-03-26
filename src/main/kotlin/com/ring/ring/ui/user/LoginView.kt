package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.loginView() {
    head {
        title {
            buildString {
                append("Login")
            }
        }
        bootstrap()
    }
    body(
        classes = "d-flex justify-content-center",
    ) {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "w-100 mt-5") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Login" }
            form(action = "api/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                div(classes = "form-floating mb-3") {
                    textInput(classes = "form-control", name = "email") {
                        id = "email"
                        placeholder = "Email address"
                    }
                    label { +"Email address" }
                }
                div(classes = "form-floating mb-3") {
                    passwordInput(classes = "form-control", name = "password") {
                        id = "password"
                        placeholder = "Password"
                    }
                    label { +"Password" }
                }
                button(classes = "w-100 btn btn-lg btn-primary", type = ButtonType.submit) {
                    +"Login"
                }
            }
            p(classes = "mt-5 mb-3 text-muted text-center") {
                +"Don't have an account? "
                a(href = "signup") { +"Sign up" }
            }
        }
    }
}