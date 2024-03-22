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
        res.todoList.forEach { todo ->
            a(href = "/todo?id=${todo.id}", classes = "card") {
                div {
                    input(type = InputType.checkBox) {
                        checked = todo.done
                        id = "todo-${todo.id}"
                    }
                    label {
                        htmlFor = "todo-${todo.id}"
                        +todo.title
                    }
                    p { +"Deadline: ${todo.deadline}" }
                }
            }
        }
        form(action = "create", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.get) {
            p {
                submitInput { value = "Create" }
            }
        }
    }
}

private fun BODY.navbar() {
    nav(classes = "navbar navbar-expand-lg navbar-light bg-light") {
        a(classes = "navbar-brand") {
            +"Sample app"
            br
            +" for architecture basic"
        }
        button(classes = "navbar-toggler") {
            span(classes = "navbar-toggler-icon")
        }
        div(classes = "collapse navbar-collapse") {
            ul(classes = "navbar-nav me-auto mb-2 mb-lg-0") {
                li(classes = "nav-item") {
                    a(
                        classes = "nav-link active",
                        href = "list"
                    ) {
                        +"Todo"
                    }
                }
                li(classes = "nav-item") {
                    a(
                        classes = "nav-link",
                        href = "../user/mypage"
                    ) {
                        +"My Page"
                    }
                }
            }
        }
    }
}