package com.ring.ring.data

import com.ring.ring.data.db.UserDataSource
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.user.GetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class User(
    val id: Long?,
    val email: String,
    val password: String,
) {
    fun toGetUser(): GetUser.Res.User {
        return GetUser.Res.User(id!!.toString(), email, password)
    }
}

class UserRepository(
    private val dataSource: UserDataSource = DataModules.userDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }

    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.upsert(user = user)
    }
}