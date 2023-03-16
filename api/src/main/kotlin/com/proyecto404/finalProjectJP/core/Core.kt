package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.domain.repositories.RepositoryProvider
import com.proyecto404.finalProjectJP.core.domain.repositories.posts
import com.proyecto404.finalProjectJP.core.domain.repositories.relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.useCases.*

class Core(config: Configuration) {
    private val authService = AuthService()
    private val users = config.repositories.users()
    private val posts = config.repositories.posts()
    private val relationships = config.repositories.relationships()

    fun signup() = SignUp(users)
    fun login() = Login(users, authService)
    fun post() = Post(posts, users, authService)
    fun read() = Read(posts, users, authService)
    fun follow() = Follow(relationships, users, authService)
    fun wall() = Wall(posts, users, relationships, authService)
    fun following() = Following(users, relationships, authService)
    fun followers() = Followers(users, relationships, authService)
    fun unfollow() = Unfollow(users, relationships, authService)

    data class Configuration(val repositories: RepositoryProvider)
}
