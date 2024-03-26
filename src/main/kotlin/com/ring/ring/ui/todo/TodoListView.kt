package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.html.*

fun HTML.todoListView(res: GetTodoList.Res) {
    head {
        title {
            buildString {
                append("Todo List")
            }
        }
        bootstrap()
    }
    body {
        navbar()
        div(classes = "container mt-4") {
            h1(classes = "h3 mb-4 text-center") { +"Todo List" }
            res.todoList.forEach { todo ->
                a(href = "/todo?id=${todo.id}", classes = "card mb-3 p-2 text-decoration-none text-dark") {
                    div(classes = "d-flex justify-content-between align-items-center") {
                        div {
                            input(type = InputType.checkBox, classes = "form-check-input me-2") {
                                checked = todo.done
                                id = "todo-${todo.id}"
                            }
                            label(classes = "form-check-label") {
                                htmlFor = "todo-${todo.id}"
                                +todo.title
                            }
                        }
                        p(classes = "mb-0") { +"Deadline: ${todo.deadline}" }
                    }
                }
            }
            form(
                action = "create",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.get,
                classes = "mt-3"
            ) {
                button(classes = "btn btn-primary", type = ButtonType.submit) { +"Create New Todo" }
            }
        }
    }
}

private fun BODY.navbar() {
    nav(classes = "navbar navbar-expand-lg navbar-dark bg-dark") {
        div(classes = "container-fluid") {
            a(classes = "navbar-brand") {
                +"Sample app"
                br
                +" for architecture basic"
            }
            button(classes = "navbar-toggler", type = ButtonType.button) {
                attributes["data-bs-toggle"] = "collapse"
                attributes["data-bs-target"] = "#navbarNav"
                attributes["aria-controls"] = "navbarNav"
                attributes["aria-expanded"] = "false"
                attributes["aria-label"] = "Toggle navigation"
                span(classes = "navbar-toggler-icon")
            }
            div(classes = "collapse navbar-collapse") {
                ul(classes = "navbar-nav me-auto") {
                    li(classes = "nav-item") {
                        a(classes = "nav-link active", href = "list") { +"Todo" }
                    }
                    li(classes = "nav-item") {
                        a(classes = "nav-link", href = "../user/mypage") { +"My Page" }
                    }
                }
            }
        }
    }
}