package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.editTodoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Edit Todo")
            }
        }
        bootstrap()
    }
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "w-100 max-width: 500px") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Edit Todo" }
            form(
                action = "api/edit",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.post,
                classes = "needs-validation"
            ) {
                div(classes = "mb-3") {
                    label(classes = "form-label") { +"Title:" }
                    textInput(classes = "form-control", name = "title") {
                        required = true
                        value = res.todo.title
                    }
                }
                div(classes = "mb-3") {
                    label(classes = "form-label") { +"Description:" }
                    textInput(classes = "form-control", name = "description") {
                        value = res.todo.description
                    }
                }
                div(classes = "mb-3 form-check") {
                    checkBoxInput(classes = "form-check-input", name = "done") {
                        value = res.todo.done
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
                        value = res.todo.deadline
                    }
                }
                hiddenInput(name = "userId") {
                    value = res.todo.userId
                }
                hiddenInput(name = "id") {
                    value = res.todo.id
                }
                button(classes = "btn btn-primary", type = ButtonType.submit) {
                    +"Edit"
                }
            }
        }
    }
}