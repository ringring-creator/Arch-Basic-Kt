package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.user.GetUser
import kotlinx.html.*

fun HTML.editUserView(res: GetUser.Res) {
    head {
        title {
            buildString {
                append("Edit User")
            }
        }
        bootstrap()
    }
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "w-100 mt-5") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Edit User" }
            form(
                action = "api/edit",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.post
            ) {
                div(classes = "form-floating mb-3") {
                    textInput(classes = "form-control", name = "email") {
                        required = true
                        value = res.user.email
                        placeholder = "Email"
                        id = "email"
                    }
                    label { +"Email" }
                }
                div(classes = "form-floating mb-3") {
                    passwordInput(classes = "form-control", name = "password") {
                        required = true
                        value = res.user.password
                        placeholder = "New Password"
                        id = "password"
                    }
                    label { +"New Password" }
                }
                hiddenInput(name = "id") {
                    value = res.user.id
                }
                button(classes = "w-100 btn btn-lg btn-primary", type = ButtonType.submit) {
                    +"Edit"
                }
            }
        }
    }
}