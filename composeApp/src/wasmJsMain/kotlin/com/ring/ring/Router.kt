package com.ring.ring

import androidx.compose.runtime.*
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
        Route.Login -> LoginScreen { setRoute(Route.SignUp) }
        Route.SignUp -> SignUpScreen { setRoute(Route.Login) }
    }
}

sealed class Route {
    object Login : Route()
    object SignUp : Route()
}