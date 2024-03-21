package com.ring.ring.data

import com.ring.ring.data.db.UserDataSource
import com.ring.ring.di.DataModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class User(
    val id: Long?,
    val email: String,
    val password: String,
)

class UserRepository(
    private val dataSource: UserDataSource = DataModules.userDataSource,
) {
    suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }

    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.upsert(user = user)
    }
}