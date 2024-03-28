package com.ring.ring.ui.todo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ring.ring.usecase.user.Login

@Composable
fun TodoListScreen(session: Login.Res.Session) {
    Text(session.toString())
}