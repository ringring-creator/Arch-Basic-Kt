package com.ring.ring.data.db

import data.db.UserQueries
import data.db.UserTable

class UserDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) {
    fun get(id: Long): User = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    fun exist(user: User): Long? = queries
        .selectIdByEmailAndPassword(user.email, user.password)
        .executeAsOneOrNull()

    fun insert(user: User) = queries.insert(
        email = user.email,
        password = user.password,
    )

    fun update(user: User) {
        val id = user.id ?: throw IllegalStateException()
        queries.update(
            email = user.email,
            password = user.password,
            id = id,
        )
    }

    fun delete(id: Long) = queries.delete(id)

    private fun convert(table: UserTable) = User(
        id = table.id,
        email = table.email,
        password = table.password,
    )
}