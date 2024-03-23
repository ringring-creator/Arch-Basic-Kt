package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.createTodoView(userId: Long) {
    head {
        title {
            buildString {
                append("Create Todo")
            }
        }
        bootstrap()
    }
    body {
        form(
            action = "api",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            p {
                +"Title: "
                textInput(name = "title") { required = true }
            }
            p {
                +"Description: "
                textInput(name = "description")
            }
            p {
                +"Done: "
                checkBoxInput(name = "done")
            }
            p {
                +"Deadline: "

                textInput(name = "deadline") {
                    attributes["type"] = "date"
                }
            }
            p {

            hiddenInput(name = "userId") {
                    value = userId.toString()
                }
            }
            p {
                submitInput { value = "Create" }
            }
        }
    }
}