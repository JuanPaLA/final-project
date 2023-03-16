package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Wall(private val posts: Posts, private val users: Users, private val relationships: Relationships, private val authService: AuthService) {

    fun exec(request: Request): Response {
        val requestUser = users.get(request.requestUserName)
        authService.validateToken(request.token, requestUser)
        val followings = relationships.getFollowings(requestUser.name)
        val posts = posts.get(followings)
        return Response(posts)
    }

    data class Request(val requestUserName: String, val token: SessionToken)
    data class Response(val timeline: List<Post>)
}
