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
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "w-100 max-width: 500px") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Create Todo" }
            form(
                action = "api",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.post,
                classes = "needs-validation"
            ) {
                div(classes = "mb-3") {
                    label(classes = "form-label") { +"Title:" }
                    textInput(classes = "form-control", name = "title") {
                        required = true
                        placeholder = "Todo title"
                    }
                }
                div(classes = "mb-3") {
                    label(classes = "form-label") { +"Description:" }
                    textInput(classes = "form-control", name = "description") {
                        placeholder = "Todo description"
                    }
                }
                div(classes = "mb-3 form-check") {
                    checkBoxInput(classes = "form-check-input", name = "done") {
                        id = "doneCheck"
                    }
                    label(classes = "form-check-label") {
                        htmlFor = "doneCheck"
                        +"Done"
                    }
                }
                div(classes = "mb-3") {
                    label(classes = "form-label") { +"Deadline:" }
                    textInput(classes = "form-control", name = "deadline") {
                        attributes["type"] = "date"
                    }
                }
                hiddenInput(name = "userId") {
                    value = userId.toString()
                }
                button(classes = "btn btn-primary", type = ButtonType.submit) {
                    +"Create"
                }
            }
        }
    }
}