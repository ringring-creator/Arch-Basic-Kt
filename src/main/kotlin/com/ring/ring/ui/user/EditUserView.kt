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
    body {
        form(
            action = "api/edit",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            p {
                +"Email: "
                textInput(name = "email") {
                    required = true
                    value = res.user.email
                }
            }
            p {
                +"Password: "
                textInput(name = "password") {
                    required = true
                    value = res.user.password
                }
            }
            p {
                hiddenInput(name = "id") {
                    value = res.user.id
                }
            }
            p {
                submitInput { value = "Edit" }
            }
        }
    }
}