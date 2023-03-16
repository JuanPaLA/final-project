package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Read(private val posts: Posts, private val users: Users, private val authService: AuthService) {

    fun exec(request: Request): Response {
        users.get(request.postAuthor)
        val requesterUser = users.get(request.userRequest)
        authService.validateToken(request.token, requesterUser)
        val posts = posts.get(request.postAuthor)
        return Response(posts)
    }

    data class Request(val userRequest: String, val postAuthor: String, val token: SessionToken)
    data class Response(val posts: List<Post>)
}
