package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.deleteTodoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Delete Todo")
            }
        }
        bootstrap()
    }
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh;"
        div(classes = "modal-dialog") {
            div(classes = "modal-content") {
                div(classes = "modal-header") {
                    h5(classes = "modal-title") { +"Confirm Deletion" }
                }
                div(classes = "modal-body") {
                    p { +"Do you want to delete the '${res.todo.title}'?" }
                }
                div(classes = "modal-footer") {
                    form(
                        action = "api/delete",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post,
                        classes = "d-inline"
                    ) {
                        hiddenInput(name = "id") {
                            value = res.todo.id
                        }
                        button(classes = "btn btn-danger", type = ButtonType.submit) { +"Yes" }
                    }
                    form(
                        action = "list",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.get,
                        classes = "d-inline"
                    ) {
                        submitInput(classes = "btn btn-secondary") { value = "No" }
                    }
                }
            }
        }
    }
}