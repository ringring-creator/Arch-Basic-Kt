package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.todoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Todo")
            }
        }
        bootstrap()
    }
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "w-100 max-width: 400px") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Todo" }
            div(classes = "mb-3") {
                label(classes = "form-label") {
                    +"Title: "
                    +res.todo.title
                }
            }
            div(classes = "mb-3") {
                label(classes = "form-label") {
                    +"Description: "
                    +res.todo.description
                }
            }
            div(classes = "mb-3") {
                label(classes = "form-label") {
                    +"Done: "
                    +res.todo.done
                }
            }
            div(classes = "mb-3") {
                label(classes = "form-label") {
                    +"Deadline: "
                    +res.todo.deadline
                }
            }
            form(
                action = "todo/edit",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.get,
                classes = "mb-2"
            ) {
                hiddenInput(name = "id") { value = res.todo.id }
                button(classes = "w-100 btn btn-lg btn-primary", type = ButtonType.submit) {
                    +"Edit"
                }
            }
            form(
                action = "todo/delete",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.get
            ) {
                hiddenInput(name = "id") { value = res.todo.id }
                button(classes = "w-100 btn btn-lg btn-danger", type = ButtonType.submit) {
                    +"Delete"
                }
            }
        }
    }
}