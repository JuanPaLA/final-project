package com.proyecto404.finalProjectJP.core.domain.repositories

import com.proyecto404.finalProjectJP.core.domain.User

interface Users {
    fun add(user: User)
    fun get(userName: String): User
    fun delete(userName: String)
    fun update(user: User)
    fun nextId(): Int
}

fun RepositoryProvider.users() = this.get<Users>()
