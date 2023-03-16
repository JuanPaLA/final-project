package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.domain.Posts
import com.proyecto404.finalProjectJP.core.domain.Relationships
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.*

class Core(private val config: Configuration) {
    private val authService = AuthService()

    fun signup() = SignUp(config.users)
    fun login() = Login(config.users, authService)
    fun post() = Post(config.posts, config.users, authService)
    fun read() = Read(config.posts, config.users, authService)
    fun follow() = Follow(config.relationships, config.users, authService)
    fun wall() = Wall(config.posts, config.users, config.relationships, authService)
    fun following() = Following(config.users, config.relationships, authService)
    fun followers() = Followers(config.users, config.relationships, authService)
    fun unfollow() = Unfollow(config.users, config.relationships, authService)

    data class Configuration(val users: Users, val posts: Posts, val relationships: Relationships)
}
