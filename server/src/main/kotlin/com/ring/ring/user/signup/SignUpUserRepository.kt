package com.ring.ring.user.signup

import com.ring.ring.di.DataModules
import com.ring.ring.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUserRepository(
    private val dataSource: SignUpUserDataSource = DataModules.signUpUserDataSource,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.insert(user = user)
    }
}