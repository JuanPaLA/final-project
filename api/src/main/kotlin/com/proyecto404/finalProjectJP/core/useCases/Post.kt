package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.PostService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Post (private val posts: Posts, private val users: Users, private val authService: AuthService) {
    private val postService = PostService()
    fun exec(request: Request) {
        val userName = request.userName
        val content = request.content
        postService.isEmpty(content)
        postService.hasValidLength(content)
        val token = request.token
        val requestUser = users.get(userName)
        authService.validateToken(token, requestUser)
        posts.add(Post(posts.nextId(), userName, content))
    }

    data class Request(val userName: String, val token: SessionToken, val content: String)
}
