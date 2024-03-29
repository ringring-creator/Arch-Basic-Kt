package com.ring.ring

import androidx.compose.runtime.*
import com.ring.ring.ui.todo.create.CreateTodoScreen
import com.ring.ring.ui.todo.list.TodoListScreen
import com.ring.ring.ui.user.login.LoginScreen
import com.ring.ring.ui.user.signup.SignUpScreen

@Composable
fun Router() {
    var route: Route by remember { mutableStateOf(Route.Login) }

    Router(
        route = route,
        setRoute = { route = it }
    )
}

@Composable
fun Router(
    route: Route,
    setRoute: (Route) -> Unit,
) {
    when (route) {
        Route.Login -> LoginScreen(
            toSignUpScreen = { setRoute(Route.SignUp) },
            toTodoListScreen = { setRoute(Route.TodoList) }
        )

        Route.SignUp -> SignUpScreen { setRoute(Route.Login) }
        is Route.TodoList -> TodoListScreen() { setRoute(Route.CreateTodo) }
        Route.CreateTodo -> CreateTodoScreen { setRoute(Route.TodoList) }
    }
}

sealed class Route {
    data object Login : Route()
    data object SignUp : Route()
    data object TodoList : Route()
    data object CreateTodo : Route()
}