package com.ring.ring

import androidx.compose.runtime.*
import com.ring.ring.ui.todo.create.CreateTodoScreen
import com.ring.ring.ui.todo.edit.EditTodoScreen
import com.ring.ring.ui.todo.list.TodoListScreen
import com.ring.ring.ui.user.edit.EditUserScreen
import com.ring.ring.ui.user.login.LoginScreen
import com.ring.ring.ui.user.logout.LogoutScreen
import com.ring.ring.ui.user.mypage.MyPageScreen
import com.ring.ring.ui.user.signup.SignUpScreen
import com.ring.ring.ui.user.withdrawal.WithdrawalScreen

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
            toTodoListScreen = { setRoute(Route.TodoList) },
        )

        Route.Logout -> LogoutScreen(
            toLoginScreen = { setRoute(Route.Login) },
            toMyPageScreen = { setRoute(Route.MyPage) }
        )

        Route.MyPage -> MyPageScreen(
            toLogoutScreen = { setRoute(Route.Logout) },
            toEditUserScreen = { setRoute(Route.EditUser) },
            toWithdrawalUserScreen = { setRoute(Route.WithdrawalUser) },
            toTodoListScreen = { setRoute(Route.TodoList) },
        )

        Route.SignUp -> SignUpScreen { setRoute(Route.Login) }
        Route.EditUser -> EditUserScreen { setRoute(Route.MyPage) }
        Route.WithdrawalUser -> WithdrawalScreen(
            toLoginScreen = { setRoute(Route.Login) },
            toMyPageScreen = { setRoute(Route.MyPage) }
        )

        Route.TodoList -> TodoListScreen(
            toCreateTodoScreen = { setRoute(Route.CreateTodo) },
            toEditTodoScreen = { setRoute(Route.EditTodo(it)) },
            toMyPageScreen = { setRoute(Route.MyPage) },
        )

        Route.CreateTodo -> CreateTodoScreen { setRoute(Route.TodoList) }
        is Route.EditTodo -> EditTodoScreen(route.todoId) { setRoute(Route.TodoList) }
    }
}

sealed class Route {
    data object Login : Route()
    data object SignUp : Route()
    data object MyPage : Route()
    data object Logout : Route()
    data object EditUser : Route()
    data object WithdrawalUser : Route()
    data object TodoList : Route()
    data object CreateTodo : Route()
    data class EditTodo(val todoId: Long) : Route()
}