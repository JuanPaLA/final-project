package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.*

class Core {
    private val users = InMemoryUsers()
    private val posts = InMemoryPosts()
    private val relationships = InMemoryRelationships()
    private val authService = AuthService()

    fun signup() = SignUp(users)
    fun login() = Login(users, authService)
    fun post() = Post(posts, users, authService)
    fun read() = Read(posts, users, authService)
    fun follow() = Follow(relationships, users, authService)
    fun wall() = Wall(posts, users, relationships, authService)
    fun following() = Following(users, relationships, authService)
    fun followers() = Followers(users, relationships, authService)
    fun unfollow() = Unfollow(users, relationships, authService)
}
