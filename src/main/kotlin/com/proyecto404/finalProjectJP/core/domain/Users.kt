package com.proyecto404.finalProjectJP.core.domain

interface Users {
    fun add(user: User)
    fun get(userId: UserId): User?
    fun remove(userId: UserId)
    fun all(): List<User>
    fun update(user: User)
}