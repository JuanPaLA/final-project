package com.proyecto404.finalProjectJP.core.domain

interface Users {
    fun add(user: User)
    fun get(userName: String): User
}
