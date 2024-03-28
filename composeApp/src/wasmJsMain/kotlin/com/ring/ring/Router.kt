package com.ring.ring

import androidx.compose.runtime.*
import com.ring.ring.ui.todo.TodoListScreen
import com.ring.ring.ui.user.login.LoginScreen
import com.ring.ring.ui.user.signup.SignUpScreen
import com.ring.ring.usecase.user.Login.Res

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
            toTodoListScreen = { setRoute(Route.TodoList(it)) }
        )

        Route.SignUp -> SignUpScreen { setRoute(Route.Login) }
        is Route.TodoList -> TodoListScreen(route.session)
    }
}

sealed class Route {
    data object Login : Route()
    data object SignUp : Route()
    data class TodoList(val session: Res.Session) : Route()
}